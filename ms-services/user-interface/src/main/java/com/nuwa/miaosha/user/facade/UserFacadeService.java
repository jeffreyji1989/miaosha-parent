package com.nuwa.miaosha.user.facade;

import com.nuwa.miaosha.user.req.UserAddReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author jijunhui
 * @version 1.0.0
 * @date 2020/7/1 20:51
 * @description 用户服务接口
 */
@FeignClient(name = "user-service", path = "user")
public interface UserFacadeService {
    /**
     * 用户注册
     *
     * @param req
     */
    String register(@RequestBody UserAddReq req);

    /**
     * 用户注册
     *
     * @param phone
     * @param pwd
     */
    String login(@RequestParam("phone") Long phone, @RequestParam("pwd") String pwd);

}
