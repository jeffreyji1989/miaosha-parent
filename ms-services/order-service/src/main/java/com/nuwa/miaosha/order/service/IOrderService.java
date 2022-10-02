package com.nuwa.miaosha.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nuwa.miaosha.order.entity.Order;
import com.nuwa.miaosha.order.req.OrderCreateReq;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author jeffrey
 * @since 2022-09-05
 */
public interface IOrderService extends IService<Order> {
    /**
     * 创建订单
     * 1. 是否存在库存
     * 2. 扣减库存
     * 3. 生成订单
     * @param req
     */
    void createOrder(OrderCreateReq req);
}
