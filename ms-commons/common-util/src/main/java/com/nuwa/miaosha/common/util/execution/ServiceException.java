package com.nuwa.miaosha.common.util.execution;

import com.nuwa.miaosha.common.util.enums.ErrorCodeEnum;
import lombok.Data;

/**
 * 业务异常
 *
 * @author jijunhui
 * @date 2020/6/20
 * @desc
 */
@Data
public class ServiceException extends RuntimeException {
    private String code;

    public ServiceException(String code, String message) {
        super(message);
        this.code = code;
    }

    public ServiceException(String message) {
        super(message);
        this.code = ErrorCodeEnum.PARAM_ERROR.getCode();
    }

    public ServiceException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getMessage());
        this.code = errorCodeEnum.getCode();
    }
}
