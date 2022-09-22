package com.nuwa.miaosha.common.web.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 这个是java自带的filter
 * 统一请求过滤器
 */
@Slf4j
public class WebRequestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("统一请求过滤器init()");
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        log.info("请求的url:{},请求方式:{}",request.getRequestURI(),request.getMethod());
        // 执行下一个过滤器
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        log.info("统一过滤器destroy()");
        Filter.super.destroy();
    }
}
