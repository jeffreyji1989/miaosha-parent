package com.nuwa.miaosha.common.web.interceptor;

import com.nuwa.miaosha.common.util.execution.ServiceException;
import com.nuwa.miaosha.common.util.jwt.JwtTokenUtils;
import com.nuwa.miaosha.common.web.bean.LoginUser;
import com.nuwa.miaosha.common.web.context.UserContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * token拦截器 spring
 */
@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisTemplate<String,Serializable> redisTemplate;
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
        // 获取token
        String token = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
//        log.info("获取到的token:{}", token);
//        RedisTemplate<String, Serializable> redisTemplate = SpringContextUtils.getBean(RedisTemplate.class, request);
        token = token.replace(JwtTokenUtils.TOKEN_PREFIX, "");
        LoginUser loginUser = (LoginUser)redisTemplate.opsForValue().get(token);
        if (loginUser != null) {
            UserContext.userThreadLocal.set(loginUser);
        } else {
            throw new ServiceException("token校验失败!");
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
