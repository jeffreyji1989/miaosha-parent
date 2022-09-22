package com.nuwa.miaosha.common.web.context;

import com.nuwa.miaosha.common.web.bean.LoginUser;

/**
 * 用户上下文
 */
public class UserContext {

    public static ThreadLocal<LoginUser> userThreadLocal = new ThreadLocal<>();

    /**
     * 获取当前用户
     * @return
     */
    public static final LoginUser getUser() {
        return userThreadLocal.get();
    }
}
