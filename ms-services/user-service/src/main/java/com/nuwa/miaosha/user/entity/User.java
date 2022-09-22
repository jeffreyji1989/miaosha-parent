package com.nuwa.miaosha.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author jeffrey
 * @since 2022-09-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id-手机号
     */
    @TableId("ID")
    private Long id;

    @TableField("NICK_NAME")
    private String nickName;

    @TableField("PASSWORD")
    private String password;

    /**
     * 盐值
     */
    @TableField("SALT")
    private String salt;

    /**
     * 头像路径
     */
    @TableField("HEAD_URI")
    private String headUri;

    @TableField("REGISTER_TIME")
    private LocalDateTime registerTime;

    /**
     * 上次登录时间
     */
    @TableField("LAST_LOGIN_TIME")
    private LocalDateTime lastLoginTime;

    /**
     * 登录次数
     */
    @TableField("LOGIN_COUNT")
    private Integer loginCount;


}
