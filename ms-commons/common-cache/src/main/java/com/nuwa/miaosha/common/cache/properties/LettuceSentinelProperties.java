package com.nuwa.miaosha.common.cache.properties;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author jijunhui
 * @Date 2021/4/11 14:02
 * @Version 1.0.0
 * @Description 哨兵模式属性
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class LettuceSentinelProperties extends LettuceSingleProperties {
    private String masterId;
}
