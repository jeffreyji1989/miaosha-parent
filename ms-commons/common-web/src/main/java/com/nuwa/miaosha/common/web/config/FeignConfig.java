package com.nuwa.miaosha.common.web.config;

import com.nuwa.miaosha.common.util.jwt.JwtTokenUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * feign配置
 */
@Configuration
public class FeignConfig implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        // 从header获取X-token
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (null == requestAttributes){
            return;
        }
        ServletRequestAttributes srat = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = srat.getRequest();
        String token = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
        if (StringUtils.hasLength(token)) {
            //将token传递出去
            requestTemplate.header(JwtTokenUtils.TOKEN_HEADER, token);
        }
    }
}
