package com.nuwa.miaosha.order.controller;


import com.nuwa.miaosha.common.util.rpc.GlobalResponse;
import com.nuwa.miaosha.order.facade.OrderFacadeService;
import com.nuwa.miaosha.order.req.OrderCreateReq;
import com.nuwa.miaosha.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author jeffrey
 * @since 2022-09-05
 */
@RestController
@RequestMapping("/order")
public class OrderController implements OrderFacadeService {

    @Autowired
    private IOrderService orderService;
    /**
     * 创建订单
     *
     * @param req
     */
    @PostMapping("create")
    @Override
    public GlobalResponse createOrder(@RequestBody OrderCreateReq req) {
        orderService.createOrder(req);
        return GlobalResponse.success();
    }
}
