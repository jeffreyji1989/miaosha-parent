package com.nuwa.miaosha.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * @author: JeffreyJi
 * @date: 2020/11/22 17:25
 * @version: 1.0
 * @description: gateway 第一个过滤器
 */
@Component
@Slf4j
public class MsGlobalFilter implements GlobalFilter, Ordered {


    /**
     * 1. header里添加 日志串号
     *
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 添加日志串号 将现在的request 变成 change对象
        exchange.getRequest().mutate().header("LOG_SERIAL_NO", UUID.randomUUID().toString()).build();
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -520;
    }
}
