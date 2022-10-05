package com.nuwa.miaosha.ms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nuwa.miaosha.common.util.execution.ServiceException;
import com.nuwa.miaosha.common.util.rpc.GlobalResponse;
import com.nuwa.miaosha.common.web.bean.LoginUser;
import com.nuwa.miaosha.common.web.context.UserContext;
import com.nuwa.miaosha.ms.cache.MsCache;
import com.nuwa.miaosha.ms.entity.Ms;
import com.nuwa.miaosha.ms.entity.MsRecord;
import com.nuwa.miaosha.ms.entity.Subject;
import com.nuwa.miaosha.ms.mapper.MsMapper;
import com.nuwa.miaosha.ms.producer.KafkaProducer;
import com.nuwa.miaosha.ms.service.IMsRecordService;
import com.nuwa.miaosha.ms.service.IMsService;
import com.nuwa.miaosha.ms.service.ISubjectService;
import com.nuwa.miaosha.order.facade.OrderFacadeService;
import com.nuwa.miaosha.order.req.OrderCreateReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 秒杀表 服务实现类
 * </p>
 *
 * @author jeffrey
 * @since 2022-09-05
 */
@Service
@Slf4j
public class MsServiceImpl extends ServiceImpl<MsMapper, Ms> implements IMsService {

    @Autowired
    private ISubjectService subjectService;
    @Autowired
    private IMsRecordService msRecordService;
    @Autowired
    private OrderFacadeService orderFacadeService;
    @Autowired
    private MsCache msCache;
    @Autowired
    private KafkaProducer kafkaProducer;

    /**
     * 根据主题id查询秒杀商品列表
     *
     * @param subjectId
     * @return
     */
    @Override
    public List<Ms> listBySubjectId(Long subjectId) {
        return list(new LambdaQueryWrapper<>(Ms.class).eq(Ms::getSubjectId, subjectId).eq(Ms::getStatus, 0));
    }

    /**
     * 根据id获取秒杀信息
     *
     * @param msId
     * @return
     */
    public Ms getCacheById(Long msId) {
        Ms ms = msCache.getMs(msId);
        if (null == ms) {
            ms = getById(msId);
            msCache.saveMs(ms);
        }
        return ms;
    }

    /**
     * 秒杀接口
     * 1. 校验活动是否在有效期内
     * 2. 校验商品是否在有效期内
     * 3. 校验商品是否还有库存
     * 4. 校验用户token是否在有效期内
     * 5. 校验是否已经秒杀过该产品
     * 6. 生成订单
     * 7. 扣减库存
     *
     * @param id
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void msKill(Long id) {
        LoginUser user = UserContext.getUser();
        log.info("当前登录用户:{}", user);
        // 秒杀数量
        int goodCount = 1;
        // 查询秒杀信息
        Ms ms = getCacheById(id);
        // 校验秒杀信息
        validateMs(ms);
        // 查询活动信息
        Subject subject = subjectService.getCacheById(ms.getSubjectId());
        // 校验活动信息
        validateSubject(subject);
        // 预先扣减库存
        long stock = msCache.reduceStock(ms.getGoodId());
        if (stock < 0) {
            throw new ServiceException("库存不足!");
        }

        try {
            LambdaQueryWrapper<Ms> queryWrapper = Wrappers.lambdaQuery(Ms.class);
            queryWrapper.eq(Ms::getId, id).ge(Ms::getStock, goodCount);
            queryWrapper.last("for update");
            Ms one = getOne(queryWrapper);
            // 扣减库存
            one.setStock(one.getStock() - 1);
//        ;
//        LambdaUpdateWrapper<Ms> updateWrapper = Wrappers.lambdaUpdate(Ms.class);
//        // 更新
//        updateWrapper.set(stock > goodCount, Ms::getStock, stock - goodCount)
//                .eq(Ms::getId, id)
//                .ge(Ms::getStock, goodCount);
            if (updateById(one)) {
                log.info("商品：{}扣减库存：{}成功，剩余库存{}！", ms.getGoodName(), goodCount, one.getStock());
            } else {
                log.warn("商品：{}扣减库存：{}失败,剩余库存{}！", ms.getGoodName(), goodCount, one.getStock());
            }

            // 生成秒杀记录
            MsRecord msRecord = new MsRecord();
            msRecord.setGoodCount(goodCount);
            BeanUtils.copyProperties(ms, msRecord);
            msRecord.setCreateTime(LocalDateTime.now());
            msRecord.setUserId(user.getId());
            msRecordService.save(msRecord);

            // 生成订单记录
            OrderCreateReq req = new OrderCreateReq();
            req.setGoodId(ms.getGoodId());
            req.setGoodCount(goodCount);
            req.setSubjectId(ms.getSubjectId());
            req.setGoodPrice(ms.getPrice());
            req.setGoodName(ms.getGoodName());
            req.setUserId(user.getId());
            GlobalResponse orderResult = orderFacadeService.createOrder(req);
            orderResult.check();
            log.info("用户:{},下单成功：{}", user.getNickName(), req);
            // 发送下单消息
            kafkaProducer.sendCreateOrderMsg(req);
            log.info("秒杀记录保存成功:{}", msRecord);
        } catch (Exception e) {
            log.error("秒杀出现异常:{},user:{},ms:{}", e.getMessage(), user, ms);
            msCache.addStock(ms.getGoodId());
        }

    }

    // 校验活动
    private void validateSubject(Subject subject) {
        if (null == subject) {
            throw new ServiceException("活动不存在");
        }
        if (subject.getBeginTime().isAfter(LocalDateTime.now())) {
            throw new ServiceException("活动还未开始");
        }
        if (subject.getEndTime().isBefore(LocalDateTime.now())) {
            throw new ServiceException("活动已经结束");
        }
        if (subject.getStatus() > 0) {
            throw new ServiceException("活动已经失效");
        }
    }

    // 校验活动
    private void validateMs(Ms msOne) {
        if (null == msOne) {
            throw new ServiceException("商品不存在");
        }
        if (msOne.getBeginTime().isAfter(LocalDateTime.now())) {
            throw new ServiceException("活动还未开始");
        }
        if (msOne.getEndTime().isBefore(LocalDateTime.now())) {
            throw new ServiceException("活动已经结束");
        }
        if (msOne.getStatus() > 0) {
            throw new ServiceException("活动已经失效");
        }
        if (msOne.getStock() <= 0) {
            throw new ServiceException("库存不足");
        }
    }
}
