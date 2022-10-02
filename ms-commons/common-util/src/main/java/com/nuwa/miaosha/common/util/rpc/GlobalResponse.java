package com.nuwa.miaosha.common.util.rpc;

import com.nuwa.miaosha.common.util.enums.ErrorCodeEnum;
import com.nuwa.miaosha.common.util.execution.CommonException;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author jijunhui
 * @date 2021/2/20
 * @desc
 */
@Data
@Accessors(chain = true)
public class GlobalResponse<T> implements Serializable {
    /**
     * 返回消息体
     */
    private T data;
    /**
     * 错误码
     */
    private String code;
    /**
     * 错误描述
     */
    private String message;
    /**
     * 请求ID
     */
    private String requestId;
    /**
     * 返回时间
     */
    private String responseTime;

    private GlobalResponse(T data) {
        this.data = data;
        this.code = ErrorCodeEnum.SUCCESS.getCode();
        this.message = ErrorCodeEnum.SUCCESS.getMessage();
    }

    private GlobalResponse() {
        this.code = ErrorCodeEnum.SUCCESS.getCode();
        this.message = ErrorCodeEnum.SUCCESS.getMessage();
    }

    private GlobalResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private GlobalResponse(ErrorCodeEnum resultCodeEnum) {
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
    }

    public static GlobalResponse fail(ErrorCodeEnum resultCodeEnum) {
        return new GlobalResponse(resultCodeEnum);
    }

    public static GlobalResponse fail(CommonException commonException) {
        return new GlobalResponse(commonException.getCode(), commonException.getMessage());
    }

    public static GlobalResponse fail(String code, String message) {
        return new GlobalResponse(code, message);
    }

    public static GlobalResponse fail(String message) {
        return new GlobalResponse(ErrorCodeEnum.PARAM_ERROR.getCode(), message);
    }

    public static GlobalResponse success(Object data) {
        return new GlobalResponse(data);
    }

    public static GlobalResponse success() {
        return new GlobalResponse();
    }

    public void check() {
        if (!ErrorCodeEnum.SUCCESS.getCode().equals(this.code)) {
            throw new CommonException(this.getCode(), this.getMessage());
        }
    }
//
//    public void checkDataEmpty(ErrorCodeEnum errorCodeEnum) {
//        check();
//        if (this.data == null) {
//            throw new CommonException(errorCodeEnum);
//        }
//    }
}
