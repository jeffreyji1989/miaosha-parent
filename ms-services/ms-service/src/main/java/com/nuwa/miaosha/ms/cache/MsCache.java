package com.nuwa.miaosha.ms.cache;

import com.nuwa.miaosha.ms.entity.Ms;
import com.nuwa.miaosha.ms.entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.Duration;

@Component
public class MsCache {
    @Autowired
    private RedisTemplate<String, Serializable> redistemplate;
    /**
     * 秒杀模块前缀
     */
    private static final String MS_PRE = "MS:MS:";
    private static final String SUBJECT_PRE = "MS:SUBJECT:";

    /**
     * 设置商品库存
     *
     * @param ms
     */
    public void setGoodStock(Ms ms) {
        redistemplate.opsForValue().set(MS_PRE + ms.getGoodId(), ms.getStock(), Duration.between(ms.getBeginTime(), ms.getEndTime()));
    }

    /**
     * 扣减库存
     *
     * @param goodId
     * @return 返回剩余库存
     */
    public long reduceStock(Long goodId) {
        return redistemplate.opsForValue().decrement(MS_PRE + goodId);
    }

    /**
     * 增加库存
     *
     * @param goodId
     * @return 返回剩余库存
     */
    public long addStock(Long goodId) {
        return redistemplate.opsForValue().increment(MS_PRE + goodId);
    }

    /**
     * 保存主题活动
     *
     * @param subject
     */
    public void saveSubject(Subject subject) {
        redistemplate.opsForValue().set(SUBJECT_PRE + subject.getId(), subject, Duration.between(subject.getBeginTime(), subject.getEndTime()));
    }

    /**
     * 获取主题活动
     *
     * @param id
     * @return
     */
    public Subject getSubject(Long id) {
        return (Subject) redistemplate.opsForValue().get(SUBJECT_PRE + id);
    }

    /**
     * 保存秒杀信息
     *
     * @param ms
     */
    public void saveMs(Ms ms) {
        redistemplate.opsForValue().set(MS_PRE + ms.getId(), ms, Duration.between(ms.getBeginTime(), ms.getEndTime()));
    }

    /**
     * 获取主题活动
     *
     * @param id
     * @return
     */
    public Ms getMs(Long id) {
        return (Ms) redistemplate.opsForValue().get(MS_PRE + id);
    }
}
