package com.nuwa.miaosha.common.cache.util;

import java.util.Map;
import java.util.Set;

/**
 * @Author jijunhui
 * @Date 2021/4/11 14:17
 * @Version 1.0.0
 * @Description 缓存帮助类
 */
public interface CacheHelper<T> {

    /**
     * 设置value 并设置过期时间
     *
     * @param key
     * @param value
     * @param expire
     * @return
     */
    void setEx(String key, Object value, long expire);

    /**
     * string set
     *
     * @param key
     * @param value
     */
    void set(String key, Object value);

    /**
     * string get
     *
     * @param key
     * @return
     */
    T get(String key);

    /**
     * 获取 bean
     *
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    T get(String key, Class<T> clazz);

    /**
     * 删除key
     *
     * @param key
     * @return key的数量
     */
    Integer del(String... key);

    /**
     * 自增
     *
     * @param key
     * @return
     */
    Long incr1(String key);

    /**
     * @param key
     * @param n
     * @return
     */
    Long incrn(String key, Long n);

    /**
     * set add
     *
     * @param key
     * @param value
     */
    void sadd(String key, Object value);

    /**
     * 查询set集合
     *
     * @param key
     * @return
     */
    Set<Object> smeembers(String key);

    /**
     * 插入到列表头部
     *
     * @param key
     * @param message
     */
    void lpush(String key, Object message);

    /**
     * 移除列表的最后一个元素
     *
     * @param key
     * @return
     */
    Object rpop(String key);

    /**
     * map set
     *
     * @param mkey
     * @param key
     * @param value
     */
    void hset(String mkey, String key, Object value);

    /**
     * 设置多个 map
     *
     * @param mkey
     * @param map
     */
    void hmset(String mkey, Map<String, String> map);

    /**
     * map get
     *
     * @param mkey
     * @param key
     * @return
     */
    String hgetString(String mkey, String key);

    String hgetBean(String mkey, String key);


}
