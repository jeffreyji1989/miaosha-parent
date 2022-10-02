package com.nuwa.miaosha.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nuwa.miaosha.common.util.execution.ServiceException;
import com.nuwa.miaosha.common.util.rpc.GlobalResponse;
import com.nuwa.miaosha.common.web.bean.LoginUser;
import com.nuwa.miaosha.common.web.context.UserContext;
import com.nuwa.miaosha.good.facade.GoodFacadeService;
import com.nuwa.miaosha.good.resp.GoodDetailResp;
import com.nuwa.miaosha.order.entity.Order;
import com.nuwa.miaosha.order.mapper.OrderMapper;
import com.nuwa.miaosha.order.req.OrderCreateReq;
import com.nuwa.miaosha.order.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author jeffrey
 * @since 2022-09-05
 */
@Service
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {
    @Autowired
    private GoodFacadeService goodFacadeService;

    /**
     * 创建订单
     * 1. 是否存在库存
     * 2. 扣减库存
     * 3. 生成订单
     *
     * @param req
     */
    @Override
    public void createOrder(OrderCreateReq req) {
        LoginUser user = UserContext.getUser();
        // 商品详情
        GlobalResponse<GoodDetailResp> goodDetailResult = goodFacadeService.getById(req.getGoodId());
        goodDetailResult.check();
        GoodDetailResp goodDetailResp = goodDetailResult.getData();
        // 请求价格 大于 实际价格
        if (req.getGoodPrice().compareTo(goodDetailResp.getPrice()) == 1) {
            throw new ServiceException("价格异常");
        }
        // 生成订单
        Order order = new Order();
        BeanUtils.copyProperties(req,order);
        order.setCreateTime(LocalDateTime.now());
        order.setGoodName(goodDetailResp.getName());
        order.setUserId(user.getId());
        order.setStatus(0);
        save(order);
        log.info("用户:{}，下单商品:{}，下单数量:{}成功！",user.getId(),goodDetailResp.getName(),req.getGoodCount());
    }
}
