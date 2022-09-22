package com.nuwa.miaosha.gateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

@Component
@ConfigurationProperties("spring.cloud.gateway")
@Valid
@RefreshScope
@Primary
/**
 * 主要解决 gateway可以动态感知 nacos里路由的配置信息
 */
public class DynamicRouteConfig extends GatewayProperties {
}
