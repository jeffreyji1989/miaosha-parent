package com.nuwa.miaosha.common.cache.properties;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author jijunhui
 * @Date 2021/4/11 14:01
 * @Version 1.0.0
 * @Description 集群属性 cluster
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class LettuceClusterProperties extends LettuceSingleProperties {
}
