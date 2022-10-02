package com.nuwa.miaosha.order.facade;

import com.nuwa.miaosha.common.util.rpc.GlobalResponse;
import com.nuwa.miaosha.order.req.OrderCreateReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 订单对外接口
 */
@FeignClient(name = "order-service", path = "order")
public interface OrderFacadeService {
    /**
     * 创建订单
     * @param req
     */
    @PostMapping("create")
    GlobalResponse createOrder(@RequestBody OrderCreateReq req);
}
