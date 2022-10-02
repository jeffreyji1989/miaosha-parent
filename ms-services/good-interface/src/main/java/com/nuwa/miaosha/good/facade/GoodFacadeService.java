package com.nuwa.miaosha.good.facade;

import com.nuwa.miaosha.common.util.rpc.GlobalResponse;
import com.nuwa.miaosha.good.resp.GoodDetailResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "good-service", path = "good")
public interface GoodFacadeService {
    /**
     * 根据id获取商品详情
     *
     * @param id
     * @return
     */
    @PostMapping("getById")
    GlobalResponse<GoodDetailResp> getById(@RequestParam("id") Long id);

    /**
     * 扣减库存
     *
     * @param id    商品id
     * @param count 购买数量
     */
    @PostMapping("reduceStock")
    GlobalResponse reduceStock(@RequestParam("id") Long id, @RequestParam("count") Integer count);
}
