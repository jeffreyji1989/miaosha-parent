package com.nuwa.miaosha.good.cache;

import com.nuwa.miaosha.good.entity.Good;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class GoodCache {
    @Autowired
    private RedisTemplate<String, Serializable> redistemplate;
    /**
     * 商品模块前缀
     */
    private static final String GOOD_PRE="MS:GOOD:";
    /**
     * 保存主题商品
     * @param good
     */
    public void saveGood(Good good){
        redistemplate.opsForValue().set(GOOD_PRE + good.getId(),good);
    }

    /**
     * 获取主题活动
     * @param id
     * @return
     */
    public Good getGood(Long id){
        return (Good) redistemplate.opsForValue().get(GOOD_PRE + id);
    }
}
