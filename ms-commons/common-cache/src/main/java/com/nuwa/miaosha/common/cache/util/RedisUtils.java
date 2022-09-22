package com.nuwa.miaosha.common.cache.util;//package com.nuwa.common.cache.util;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.nuwa.common.global.enums.ErrorCodeEnum;
//import com.nuwa.common.global.expection.ServiceException;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//
//import java.util.Arrays;
//import java.util.Map;
//import java.util.Set;
//import java.util.concurrent.TimeUnit;
//
///**
// * @author jijunhui
// * @date 2020/11/20
// * @desc
// */
//@Component
//@Slf4j
//public class RedisUtils {
//    /**
//     * REDIS KEY 前缀
//     */
//    private static final String REDIS_KEY_PRE = "NUWA:";
//
//    @Autowired
//    private RedisTemplate<String, Object> redisTemplate;
//
//    /**
//     * 设置value 并设置过期时间
//     *
//     * @param key
//     * @param value
//     * @param expire
//     * @return
//     */
//    public void setEx(String key, Object value, long expire) {
//        redisTemplate.opsForValue().set(getKey(key), value, expire, TimeUnit.SECONDS);
//    }
//
//    /**
//     * string set
//     *
//     * @param key
//     * @param value
//     */
//    public void set(String key, Object value) {
//        redisTemplate.opsForValue().set(getKey(key), value);
//    }
//
//    /**
//     * string get
//     *
//     * @param key
//     * @return
//     */
//    public String get(String key) {
//        Object value = redisTemplate.opsForValue().get(getKey(key));
//        return value instanceof String ? value.toString() : null;
//    }
//
//    /**
//     * 获取 bean
//     *
//     * @param key
//     * @param clazz
//     * @param <T>
//     * @return
//     */
//    public <T> T get(String key, Class<T> clazz) {
//        Object value = redisTemplate.opsForValue().get(getKey(key));
//        if (null == value) {
//            return null;
//        }
//        return new ObjectMapper().convertValue(value, clazz);
//    }
//
//    /**
//     * 删除key
//     *
//     * @param key
//     * @return key的数量
//     */
//    public Integer del(String... key) {
//        if (StringUtils.isEmpty(key)) {
//            throw new ServiceException(ErrorCodeEnum.SYSTEM_ERROR);
//        }
//        Arrays.stream(key).forEach(k -> redisTemplate.delete(getKey(k)));
//        return key.length;
//    }
//
//    /**
//     * 自增
//     *
//     * @param key
//     * @return
//     */
//    public Long incr1(String key) {
//        return redisTemplate.opsForValue().increment(getKey(key));
//    }
//
//    /**
//     * @param key
//     * @param n
//     * @return
//     */
//    public Long incrn(String key, Long n) {
//        return redisTemplate.opsForValue().increment(getKey(key), n);
//    }
//
//    /**
//     * set add
//     *
//     * @param key
//     * @param value
//     */
//    public void sadd(String key, Object value) {
//        redisTemplate.opsForSet().add(getKey(key), value);
//    }
//
//    /**
//     * 查询set集合
//     *
//     * @param key
//     * @return
//     */
//    public Set<Object> smeembers(String key) {
//        return redisTemplate.opsForSet().members(getKey(key));
//    }
//
//    /**
//     * 插入到列表头部
//     *
//     * @param key
//     * @param message
//     */
//    public void lpush(String key, Object message) {
//        redisTemplate.opsForList().leftPush(getKey(key), message);
//    }
//
//    /**
//     * 移除列表的最后一个元素
//     *
//     * @param key
//     * @return
//     */
//    public Object rpop(String key) {
//        return redisTemplate.opsForList().rightPop(getKey(key));
//    }
//
//    /**
//     * map set
//     *
//     * @param mkey
//     * @param key
//     * @param value
//     */
//    public void hset(String mkey, String key, Object value) {
//        redisTemplate.opsForHash().put(getKey(mkey), key, value);
//    }
//
//    /**
//     * 设置多个 map
//     *
//     * @param mkey
//     * @param map
//     */
//    public void hmset(String mkey, Map<String, String> map) {
//        redisTemplate.opsForHash().putAll(getKey(mkey), map);
//    }
//
//    /**
//     * map get
//     *
//     * @param mkey
//     * @param key
//     * @return
//     */
//    public String hgetString(String mkey, String key) {
//        Object value = redisTemplate.opsForHash().get(getKey(mkey), key);
//        return value instanceof String ? value.toString() : null;
//    }
//
//    public String hgetBean(String mkey, String key) {
//        Object value = redisTemplate.opsForHash().get(getKey(mkey), key);
//        return value instanceof String ? value.toString() : null;
//    }
//
////    public Map<String, String> hgetAll(String key) {
////        try {
////            Map<String, String> stringStringMap = cluster.hgetAll(key);
////            return stringStringMap;
////        } catch (RuntimeException e) {
////            log.error("redis hashgetAll错误：{}", e);
////        }
////        return null;
////    }
////
////    public Long hdel(String mkey, String... key) {
////        try {
////            Long hdel = cluster.hdel(mkey, key);
////            return hdel;
////        } catch (RuntimeException e) {
////            log.error("redis hashdel错误：{}", e);
////        }
////        return null;
////    }
//
//
////    public <T> T getCustomizeRedis(String key, TypeReference<T> typeReference, CustomizeRedis<T> customizeRedis) {
////        T t = get(key, typeReference);
////        if (t == null) {
////            t = customizeRedis.customize();
////            if (t != null) {
////                set(key, t);
////            }
////        }
////        return t;
////    }
//
//    /**
//     * 设置key的前缀
//     *
//     * @param key
//     * @return
//     */
//    private static final String getKey(String key) {
//        if (StringUtils.isEmpty(key)) {
//            log.error("redis key is null");
//            throw new ServiceException(ErrorCodeEnum.SYSTEM_ERROR);
//        }
//        return REDIS_KEY_PRE + key;
//    }
//
////    @FunctionalInterface
////    public interface CustomizeRedis<T> {
////        T customize();
////    }
//}
