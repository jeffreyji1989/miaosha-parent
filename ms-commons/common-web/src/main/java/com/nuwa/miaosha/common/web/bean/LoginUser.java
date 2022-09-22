package com.nuwa.miaosha.common.web.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginUser implements Serializable {
    private Long id;
    private String nickName;
}
