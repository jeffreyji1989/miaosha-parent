package com.nuwa.miaosha.common.util.enums;

import lombok.Getter;

/**
 * @author jijunhui
 * @date 2020/6/20
 * @desc
 */
@Getter
public enum ErrorCodeEnum {
    SUCCESS("200", "success"),
    URL_NOT_FOUND("404", "没有找到对应的服务地址"),
    METHOD_NOT_SUPPORT("405", "不支持此类型请求"),
    SYSTEM_ERROR("500", "系统错误，请稍后再试"),
    SERVICE_NOT_ROLE("401", "服务未授权"),
    REQUEST_EMOJI_ERROR("10000000", "请求包含非法字符"),
    PARAM_ERROR("10000001", "参数异常"),
    OSS_ERROR("10000002", "对象存储异常"),
    NO_PERMISSION("10000005", "无访问权限，请联系管理员处理"),
    SECRET_ENCRY_FAIL("99999998", "加密出现错误"),
    COMMON_ERROR("99999999", "通用错误码"),
    CURRENT_NO_PROD("10000007", "非prod环境不能执行此操作"),
    GET_LOCK_FAIL("10000008", "获取锁失败"),

    /** 业务错误码100开头 **/
    /**
     * 开票服务错误码 10 三位错误码
     **/
    SECKILL_GOOD_NOT_FOUNT("10010001", "商品不存在"),
    SECKILL_GOOD_NOT_STORE("10010001", "商品库存不足"),

    ;

    private String code;
    private String message;

    ErrorCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
