package com.nuwa.miaosha.ms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nuwa.miaosha.common.util.execution.ServiceException;
import com.nuwa.miaosha.ms.entity.Ms;
import com.nuwa.miaosha.ms.entity.Subject;
import com.nuwa.miaosha.ms.mapper.MsMapper;
import com.nuwa.miaosha.ms.service.IMsService;
import com.nuwa.miaosha.ms.service.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class MsServiceImpl extends ServiceImpl<MsMapper, Ms> implements IMsService {

    @Autowired
    private ISubjectService subjectService;

    /**
     * 根据主题id查询秒杀商品列表
     *
     * @param subjectId
     * @return
     */
    @Override
    public List<Ms> listBySubjectId(Long subjectId) {
        return list(new LambdaQueryWrapper<>(Ms.class).eq(Ms::getSubjectId,subjectId).eq(Ms::getStatus,0));
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
    @Override
    public void msKill(Long id) {
        // 查询秒杀信息
        Ms ms = getById(id);
        // 校验秒杀信息
        validateMs(ms);
        // 查询活动信息
        Subject subject = subjectService.getById(ms.getSubjectId());
        // 校验活动信息
        validateSubject(subject);

        // 查询订单信息
        String userId = "";

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
        if (msOne.getStatus() <=0){
            throw new ServiceException("库存不足");
        }
    }
}
