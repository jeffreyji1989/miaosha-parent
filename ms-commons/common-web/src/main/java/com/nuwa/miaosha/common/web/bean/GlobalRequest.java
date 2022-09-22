package com.nuwa.miaosha.common.web.bean;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * 统一请求处理
 * @author jeffrey
 */
@Data
public class GlobalRequest<T> implements Serializable {
    /**
     * 请求头信息
     */
    @JSONField(ordinal = 1)
    private JSONObject header;
    /**
     * 请求体
     */
    @JSONField(ordinal = 2)
    private T body;
    /**
     * MD5签名
     */
    @JSONField(ordinal = 3)
    private String sign;
}
