package com.nuwa.miaosha.common.web.advice;

import com.alibaba.fastjson.JSON;
import com.nuwa.miaosha.common.util.constant.CommonConstants;
import com.nuwa.miaosha.common.util.rpc.GlobalResponse;
import com.nuwa.miaosha.common.web.util.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @author jeffrey
 */
@RestControllerAdvice
@Slf4j
public class ControllerResponseAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public GlobalResponse beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        HttpServletRequest httpServletRequest = SpringContextUtils.getHttpServletRequest();

        GlobalResponse responseDto;
        if (null == body || body instanceof Void){
            responseDto = GlobalResponse.success(null == body ? new HashMap<>() : body);
        }else if(body instanceof GlobalResponse){
            responseDto = (GlobalResponse) body;
        }else{
            responseDto = GlobalResponse.success(body);
        }
        responseDto.setResponseTime(System.currentTimeMillis() + "");
        responseDto.setRequestId(TraceContext.traceId());
        String s = JSON.toJSONString(body);
        log.info("请求URL:{},返回结果:{}", httpServletRequest.getRequestURL(), s.length() > CommonConstants.LOG_LENGTH ? s.length() : s);
        return responseDto;
    }
}
