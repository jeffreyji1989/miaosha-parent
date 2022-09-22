package com.nuwa.miaosha.common.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 日志拦截器 spring
 */
@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    /**
     * 耗时
     */
    private static final ThreadLocal<Long> timeThreadLocal = new ThreadLocal();

    /**
     * 请求之前执行
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("请求url:{}", request.getRequestURL());
        timeThreadLocal.set(System.currentTimeMillis());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("请求url:{},耗时:{} ms", request.getRequestURL(), System.currentTimeMillis() - timeThreadLocal.get());
        timeThreadLocal.remove();
    }
}
