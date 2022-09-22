package com.nuwa.miaosha.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nuwa.miaosha.user.entity.User;
import com.nuwa.miaosha.user.req.UserAddReq;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jeffrey
 * @since 2022-09-05
 */
public interface IUserService extends IService<User> {
    /**
     * 用户注册
     * @param req
     */
    String register(UserAddReq req);

    /**
     * 用户注册
     * @param phone
     * @param pwd
     */
    String login(Long phone,String pwd);

}
