package com.nuwa.miaosha.common.util.spring;//package com.nuwa.common.util.spring;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpRequest;
//import org.springframework.util.StringUtils;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * @Author jijunhui
// * @Date 2021/1/24 15:21
// * @Version 1.0.0
// * @Description spring上下文工具类
// */
//@Slf4j
//public class SpringContextUtils {
//    /**
//     * 获取servletRequest
//     *
//     * @return
//     */
//    public final static HttpServletRequest getHttpServletRequest() {
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        if (null == attributes) {
//            return null;
//        }
//        return attributes.getRequest();
//    }
//
//    /**
//     * 获取servletResponse
//     *
//     * @return
//     */
//    public final static HttpServletResponse getHttpServletResponse() {
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        if (null == attributes) {
//            return null;
//        }
//        return attributes.getResponse();
//    }
//
//    /**
//     * 获取token
//     *
//     * @return
//     */
//    public final static String getRequestHeaderToken(HttpRequest httpRequest) {
//        // 获取token信息
//        String token = httpRequest.getHeaders().getFirst(JwtTokenUtils.TOKEN_HEADER);
//        if (!StringUtils.isEmpty(token) && token.startsWith(JwtTokenUtils.TOKEN_PREFIX)) {
//            token = token.replace(JwtTokenUtils.TOKEN_PREFIX, "");
//        } else {
//            log.error("url:{}请求url中获取到的token 不合法:{}", httpRequest.getURI(), token);
//            token = null;
//        }
//        return token;
//    }
//
//    /**
//     * 获取token
//     *
//     * @return
//     */
//    public final static String getRequestHeaderToken(HttpServletRequest httpRequest) {
//        // 获取token信息
//        String token = httpRequest.getHeader(JwtTokenUtils.TOKEN_HEADER);
//        if (!StringUtils.isEmpty(token) && token.startsWith(JwtTokenUtils.TOKEN_PREFIX)) {
//            token = token.replace(JwtTokenUtils.TOKEN_PREFIX, "");
//        } else {
//            log.error("url:{}请求url中获取到的token 不合法:{}", httpRequest.getRequestURI(), token);
//            token = null;
//        }
//        return token;
//    }
//
//
//}
