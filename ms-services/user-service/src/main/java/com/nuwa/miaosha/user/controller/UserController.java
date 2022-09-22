package com.nuwa.miaosha.user.controller;

import com.nuwa.miaosha.user.facade.UserFacadeService;
import com.nuwa.miaosha.user.req.UserAddReq;
import com.nuwa.miaosha.user.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jeffrey
 * @since 2022-09-05
 */
@RestController
@RequestMapping("/user")
@Slf4j
@RefreshScope
public class UserController implements UserFacadeService {
    @Autowired
    private IUserService userService;

    @Value("${k1:111}")
    private String k1;

    @PostMapping("register")
    public String register(@RequestBody UserAddReq req) {
        return userService.register(req);
    }

    @PostMapping("login")
    public String login(@RequestParam("phone") Long phone, @RequestParam("pwd") String pwd) {
        return userService.login(phone, pwd);
    }

}
