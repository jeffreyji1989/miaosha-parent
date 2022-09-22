package com.nuwa.miaosha.common.web.advice;

import com.nuwa.miaosha.common.util.enums.ErrorCodeEnum;
import com.nuwa.miaosha.common.util.execution.CommonException;
import com.nuwa.miaosha.common.util.execution.ServiceException;
import com.nuwa.miaosha.common.web.bean.GlobalResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @author jeffrey
 */
@Slf4j
@RestControllerAdvice
public class WebExceptionAdvice {
    /**
     * 业务异常
     *
     * @param commonException
     * @return
     */
    @ExceptionHandler({ServiceException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public GlobalResponse processMessage(ServiceException commonException) {
        log.error("业务异常：错误码={}，错误描述={}", commonException.getCode(), commonException.getMessage(), commonException);
        return GlobalResponse.fail(commonException.getCode(), commonException.getMessage());
    }
    /**
     * 业务异常
     *
     * @param commonException
     * @return
     */
    @ExceptionHandler({CommonException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public GlobalResponse processMessage(CommonException commonException) {
        log.error("业务异常：错误码={}，错误描述={}", commonException.getCode(), commonException.getMessage(), commonException);
        return GlobalResponse.fail(commonException.getCode(), commonException.getMessage());
    }

    /**
     * 方法不支持
     *
     * @param exception
     * @return
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public GlobalResponse paramError(HttpRequestMethodNotSupportedException exception) {
        log.error("方法不支持：{}", exception.getMessage(), exception);
        return GlobalResponse.fail(ErrorCodeEnum.METHOD_NOT_SUPPORT);
    }

    /**
     * 系统异常
     *
     * @param exception
     * @return
     */
    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public GlobalResponse processMessage(Exception exception) {
        log.error("系统异常：错误信息={}", exception.getMessage(), exception);
        return GlobalResponse.fail(ErrorCodeEnum.SYSTEM_ERROR);
    }

    /**
     * 地址不存在异常
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public GlobalResponse notFountHandler(NoHandlerFoundException exception) {
        log.error("地址不存在：{}", exception.getMessage(), exception);
        return GlobalResponse.fail(ErrorCodeEnum.URL_NOT_FOUND);
    }
}
