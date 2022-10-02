package com.nuwa.miaosha.good.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nuwa.miaosha.common.util.execution.ServiceException;
import com.nuwa.miaosha.good.cache.GoodCache;
import com.nuwa.miaosha.good.entity.Good;
import com.nuwa.miaosha.good.mapper.GoodMapper;
import com.nuwa.miaosha.good.service.IGoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jeffrey
 * @since 2022-09-05
 */
@Service
@Slf4j
public class GoodServiceImpl extends ServiceImpl<GoodMapper, Good> implements IGoodService {

    @Autowired
    private GoodCache goodCache;

    /**
     * 扣减库存
     *
     * @param id    商品id
     * @param count 购买数量
     */
    @Override
    public void reduceStock(Long id, Integer count) {
        Good good = getById(id);
        if (null == good) {
            throw new ServiceException("商品不存在");
        }
        if (good.getStock() < count) {
            throw new ServiceException("库存不足");
        }

        LambdaUpdateWrapper<Good> updateWrapper = Wrappers.lambdaUpdate(Good.class);
        // 更新
        updateWrapper.set(good.getStock() > count, Good::getStock, good.getStock() - count)
                .eq(Good::getId, id)
                .eq(Good::getStock, good.getStock());
        if (update(updateWrapper)) {
            log.info("商品：{}扣减库存：{}成功，剩余库存{}！", good.getName(), count, good.getStock());
        } else {
            log.warn("商品：{}扣减库存：{}失败,剩余库存{}！", good.getName(), count, good.getStock());
        }

    }

    /**
     * 查询商品信息(缓存)
     *
     * @param goodId
     * @return
     */
    @Override
    public Good getCacheById(Long goodId) {
        Good good = goodCache.getGood(goodId);
        if (null == good) {
            good = getById(goodId);
            goodCache.saveGood(good);
        }
        return good;
    }
}
