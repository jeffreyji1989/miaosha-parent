package com.nuwa.miaosha.ms.init;

import com.nuwa.miaosha.ms.cache.MsCache;
import com.nuwa.miaosha.ms.entity.Ms;
import com.nuwa.miaosha.ms.service.IMsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 商品库存初始化
 */
@Component
@Slf4j
public class GoodStockInit implements SmartLifecycle {
    private boolean started = false;
    @Autowired
    private IMsService msService;
    @Autowired
    private MsCache msCache;

    @Override
    public void start() {
        // 查询所有要促销的商品
        List<Ms> list = msService.list();
        list.forEach(ms -> {
            msCache.setGoodStock(ms);
        });
        log.info("所有商品库存初始化完毕！");
        started = true;
    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return started;
    }
}
