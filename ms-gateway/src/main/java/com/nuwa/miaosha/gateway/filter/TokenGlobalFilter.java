package com.nuwa.miaosha.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.nuwa.miaosha.common.util.constant.CommonConstants;
import com.nuwa.miaosha.common.util.jwt.JwtTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * @author jijunhui
 * @version 1.0.0
 * @date 2020/11/19 16:58
 * @description token检验Filter
 */
@Component
@Slf4j
public class TokenGlobalFilter implements GlobalFilter, Ordered {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Value("${white.list.uri}")
    private String whiteListUrl;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpResponse serverHttpResponse = exchange.getResponse();
        ServerHttpRequest request = exchange.getRequest();
        String url = request.getURI().getPath();
        log.info("gateway 拦截到的url:{}", url);
        // 如果是白名单里的url则不拦截
        if (StringUtils.hasLength(whiteListUrl)) {
            for (String uri : whiteListUrl.split(",")) {
                if (antPathMatcher.match(uri, url)) {
                    return chain.filter(exchange);
                }
            }
        }
        // 获取token信息
        String token = request.getHeaders().getFirst(JwtTokenUtils.TOKEN_HEADER);
//        // 如果 token不存在，或者token不合法 则直接返回
        if (!StringUtils.hasLength(token) || !token.startsWith(JwtTokenUtils.TOKEN_PREFIX)) {
            return out(serverHttpResponse);
        }
        token = token.replace(JwtTokenUtils.TOKEN_PREFIX, "");
        String userId = JwtTokenUtils.getUsername(token);
        // 校验token
        if (null == userId) {
            return out(serverHttpResponse);
        }
        String userStr = redisTemplate.opsForValue().get(CommonConstants.CACHE_PRE + userId);
        if (null == userStr || !StringUtils.hasLength(userStr)){
            return out(serverHttpResponse);
        }
        log.info("token校验成功，result:{}", userId);

        // 去redis中查到相关信息，并校验权限
        return chain.filter(exchange);
    }

    private Mono<Void> out(ServerHttpResponse response) {
        JSONObject message = new JSONObject();
        message.put("success", false);
        message.put("code", 28004);
        message.put("data", "鉴权失败");
        byte[] bits = message.toString().getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bits);
        //response.setStatusCode(HttpStatus.UNAUTHORIZED);
        //指定编码，否则在浏览器中会中文乱码
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        return response.writeWith(Mono.just(buffer));
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
