package com.nuwa.miaosha.common.web.config;

import com.nuwa.miaosha.common.util.constant.SeparatorConstants;
import com.nuwa.miaosha.common.web.interceptor.LogInterceptor;
import com.nuwa.miaosha.common.web.interceptor.TokenInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.Reference;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * spring 拦截器配置类
 */
@Configuration
@Slf4j
public class InterceptorConfig implements WebMvcConfigurer {
    @Value("${white.list.url:''}")
    private String whiteList;
    @Autowired
    private TokenInterceptor tokenInterceptor;
    /**
     * 添加日志拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .addPathPatterns("/**");
        log.info("whiteList:{}",whiteList);
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**").excludePathPatterns(whiteList.split(SeparatorConstants.COMMA));
    }
}
