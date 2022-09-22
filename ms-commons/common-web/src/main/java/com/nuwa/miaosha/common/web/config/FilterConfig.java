package com.nuwa.miaosha.common.web.config;

import com.nuwa.miaosha.common.web.filter.WebRequestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 过滤器配置类
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean webRequestFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebRequestFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setName("webRequestFilter");
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }

}
