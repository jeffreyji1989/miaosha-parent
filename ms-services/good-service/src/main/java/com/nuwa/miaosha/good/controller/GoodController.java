package com.nuwa.miaosha.good.controller;


import com.nuwa.miaosha.common.util.bean.BeanUtils;
import com.nuwa.miaosha.common.util.execution.ServiceException;
import com.nuwa.miaosha.common.util.rpc.GlobalResponse;
import com.nuwa.miaosha.good.entity.Good;
import com.nuwa.miaosha.good.facade.GoodFacadeService;
import com.nuwa.miaosha.good.resp.GoodDetailResp;
import com.nuwa.miaosha.good.service.IGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jeffrey
 * @since 2022-09-05
 */
@RestController
@RequestMapping("/good")
public class GoodController implements GoodFacadeService {
    @Autowired
    private IGoodService goodService;

    /**
     * 根据id获取商品详情
     *
     * @param id
     * @return
     */
    @PostMapping("getById")
    @Override
    public GlobalResponse<GoodDetailResp> getById(Long id) {
        // 根据id获取商品信息
        Good good = goodService.getCacheById(id);
        if (null == good) {
            throw new ServiceException("商品不存在");
        }
        return GlobalResponse.success(BeanUtils.conversion(good, GoodDetailResp.class));
    }

    /**
     * 扣减库存
     *
     * @param id    商品id
     * @param count 购买数量
     */
    @PostMapping("reduceStock")
    @Override
    public GlobalResponse reduceStock(Long id, Integer count) {
        goodService.reduceStock(id,count);
        return GlobalResponse.success();
    }

    @PostMapping("save")
    public void save(@RequestBody Good good){
        goodService.save(good);
    }
}
