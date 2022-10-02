package com.nuwa.miaosha.good.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nuwa.miaosha.good.entity.Good;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jeffrey
 * @since 2022-09-05
 */
public interface IGoodService extends IService<Good> {
    /**
     * 扣减库存
     * @param id 商品id
     * @param count 购买数量
     */
    void reduceStock(Long id, Integer count);

    /**
     * 查询商品信息(缓存)
     * @param goodId
     * @return
     */
    Good getCacheById(Long goodId);

}
