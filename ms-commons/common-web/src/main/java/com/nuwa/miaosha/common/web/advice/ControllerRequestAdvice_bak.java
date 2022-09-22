//package com.nuwa.miaosha.common.web.advice;
//
//import com.alibaba.fastjson.JSONObject;
//import com.nuwa.miaosha.common.util.constant.CommonConstants;
//import com.nuwa.miaosha.common.util.constant.SeparatorConstants;
//import com.nuwa.miaosha.common.util.io.IOUtils;
//import com.nuwa.miaosha.common.web.bean.GlobalRequest;
//import com.nuwa.miaosha.common.web.util.SpringContextUtils;
//import com.nuwa.miaosha.common.web.util.ValidationUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.BeanUtils;
//import org.springframework.core.MethodParameter;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpInputMessage;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;
//
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//import java.io.InputStream;
//import java.lang.reflect.Type;
//
///**
// * @author jeffrey
// */
//@RestControllerAdvice
//@Slf4j
//public class ControllerRequestAdvice_bak implements RequestBodyAdvice {
//
//    @Override
//    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
//        return true;
//    }
//
//    @Override
//    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
//        return new HttpInputMessage() {
//            @Override
//            public InputStream getBody() throws IOException {
//                HttpServletRequest request = SpringContextUtils.getHttpServletRequest();
//
//                // 从header中获取token信息，然后校验token是否有效 如果有效则取出userId信息
////                String token = SpringContextUtils.getRequestHeaderToken(request);
////                String userId = "";
////                // 在缓存中查询到 用户信息
////                if (!StringUtils.isEmpty(token)) {
////                    userId = token;
////                }
//                StringBuffer logStr = new StringBuffer();
//                logStr.append("请求URL:").append(request.getRequestURL());
//                logStr.append(",请求方法:").append(parameter.getMethod().getDeclaringClass().getName())
//                        .append(SeparatorConstants.SPOT).append(parameter.getMethod().getName()).append(SeparatorConstants.BRACKETS);
//                // 从流中获取参数
//                InputStream inputStream = inputMessage.getBody();
//                String result = IOUtils.inputStreamToString(inputStream);
//                logStr.append(" 请求参数:").append(result.length() > CommonConstants.LOG_LENGTH ? result.length() + " , 可能包含图片" : result);
//                // 打印请求参数
//                log.info("{}", logStr);
//
//                // 转换为Req对象
//                GlobalRequest<?> apiVoReq = (GlobalRequest<?>) JSONObject.parseObject(result, GlobalRequest.class);
//                // 获取header
//                JSONObject headReq = apiVoReq.getHeader();
//                if (null == headReq) {
//                    log.error("非法请求 header is null");
////                    throw new ServiceException(ErrorCodeEnum.PARAM_ERROR);
//                }
//                // 设置IP地址
////                headReq.put("requestIp", NetUtils.getIpAddress());
//                // 设置userId
////                headReq.put("userId", userId);
//                Object body = apiVoReq.getBody();
//                Class<?>[] value = {};
//                // header body 参数校验
//                if (null != headReq){
//                    ValidationUtils.validation(headReq, value);
//                }
//                if (null != body) {
////                    ParameterizedType pt = (ParameterizedType) parameter.getParameter().getParameterizedType();
////                    ValidationUtils.validation(JSONObject.parseObject(JSONObject.toJSONString(body), (Class) pt.getActualTypeArguments()[0]), value);
//                    // 属性copy
//                    Class<?> parameterType = parameter.getParameterType();
//                    body = JSONObject.parseObject(JSONObject.toJSONString(body), parameterType);
//                    Object headerObj = JSONObject.parseObject(JSONObject.toJSONString(headReq), parameterType.getSuperclass());
////                    body = requestJSONObject.get("body");
////                    if (!(headerObj instanceof JSONObject)) {
////                        BeanUtils.copyProperties(headerObj, body, com.nuwa.common.global.util.BeanUtils.getNullPropertyNames(headerObj));
////                    } else {
////                        BeanUtils.copyProperties(headerObj, body);
////                    }
//                    BeanUtils.copyProperties(headerObj, body);
//
//                    ValidationUtils.validation(JSONObject.parseObject(JSONObject.toJSONString(body), parameterType), value);
//                }
//
////                tempHead.setTokenId(headReq.getTokenId());
////                tempHead.setUserId(headReq.getUserId());
//
//                // 把header里的属性重新序列化为json
//                result = JSONObject.toJSONString(body);
//                // 返回结果
//                return IOUtils.StringToInputStream(result);
//
//            }
//
//            @Override
//            public HttpHeaders getHeaders() {
//                return inputMessage.getHeaders();
//            }
//        };
//    }
//
//    @Override
//    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
//        return body;
//    }
//
//    @Override
//    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
//        return body;
//    }
//}
