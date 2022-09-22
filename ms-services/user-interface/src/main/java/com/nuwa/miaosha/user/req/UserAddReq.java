package com.nuwa.miaosha.user.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class UserAddReq implements Serializable {
    @NotNull(message = "手机号不能为空")
    private Long phone;
    @NotBlank(message = "昵称不能为空")
    private String nickName;
    @NotBlank(message = "密码不能为空")
    private String password;
}
