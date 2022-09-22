package com.nuwa.miaosha.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.hash.Hashing;
import com.nuwa.miaosha.common.util.bean.BeanUtils;
import com.nuwa.miaosha.common.util.execution.ServiceException;
import com.nuwa.miaosha.common.util.jwt.JwtTokenUtils;
import com.nuwa.miaosha.user.cache.UserCache;
import com.nuwa.miaosha.user.entity.User;
import com.nuwa.miaosha.user.mapper.UserMapper;
import com.nuwa.miaosha.user.req.UserAddReq;
import com.nuwa.miaosha.user.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.time.LocalDateTime;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jeffrey
 * @since 2022-09-05
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserCache userCache;

    /**
     * 用户注册
     *
     * @param req
     */
    @Override
    public String register(UserAddReq req) {
        User currentUser = baseMapper.selectById(req.getPhone());
        if (null != currentUser) {
            log.error("用户:{}已经注册", req);
            throw new ServiceException("用户已经注册");
        }
        User user = BeanUtils.conversion(req, User.class);
        user.setRegisterTime(LocalDateTime.now());
        user.setLastLoginTime(user.getRegisterTime());
        user.setPassword(pwdMd5(user.getPassword()));
        user.setId(req.getPhone());
        String token = JwtTokenUtils.createToken(user.getId().toString());
        baseMapper.insert(user);
        // 这里要加入redis缓存
        userCache.saveToken(user.getId().toString(), token, JwtTokenUtils.EXPIRITION);
        userCache.saveUser(token, user);
        return token;
    }

    /**
     * 用户注册
     *
     * @param phone
     * @param pwd
     */
    @Override
    public String login(Long phone, String pwd) {
        User currentUser = baseMapper.selectById(phone);
        if (null == currentUser) {
            log.error("用户:{}还未注册", phone);
            throw new ServiceException("用户还未注册");
        }
        if (!currentUser.getPassword().equals(pwdMd5(pwd))) {
            log.warn("用户名/密码不正确");
            throw new ServiceException("用户名/密码不正确");
        }

        String token = JwtTokenUtils.createToken(currentUser.getId().toString());
        // 这里要加入redis缓存
        userCache.saveToken(currentUser.getId().toString(), token, JwtTokenUtils.EXPIRITION);
        currentUser.setLastLoginTime(LocalDateTime.now());
        currentUser.setLoginCount(currentUser.getLoginCount() + 1);
        baseMapper.updateById(currentUser);
        userCache.saveUser(token, currentUser);
        return token;
    }

    /**
     * 获取md5
     *
     * @param password
     * @return
     */
    private String pwdMd5(String password) {
        return Hashing.md5().newHasher().putString(password, Charset.defaultCharset()).hash().toString();
    }
}
