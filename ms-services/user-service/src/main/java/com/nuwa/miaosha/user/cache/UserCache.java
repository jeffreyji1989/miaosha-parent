package com.nuwa.miaosha.user.cache;

import com.nuwa.miaosha.common.util.bean.BeanUtils;
import com.nuwa.miaosha.common.util.jwt.JwtTokenUtils;
import com.nuwa.miaosha.common.web.bean.LoginUser;
import com.nuwa.miaosha.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

@Component
public class UserCache {
    @Autowired
    private RedisTemplate<String, Serializable> redistemplate;

    /**
     * 保存token
     *
     * @param userId
     * @param token
     * @param expired
     */
    public void saveToken(String userId, String token, long expired) {
        redistemplate.opsForValue().set(userId,token,expired, TimeUnit.MILLISECONDS);
    }

    /**
     * 保存用户信息
     * @param token
     * @param user
     */
    public void saveUser(String token, User user){
        LoginUser loginUser = BeanUtils.conversion(user,LoginUser.class);
        redistemplate.opsForValue().set(token,loginUser,JwtTokenUtils.EXPIRITION,TimeUnit.MILLISECONDS);
    }
}
