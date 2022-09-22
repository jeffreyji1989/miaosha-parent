package com.nuwa.miaosha.user.controller;

import com.nuwa.miaosha.common.cache.util.RedissonUtil;
import com.nuwa.miaosha.user.facade.UserFacadeService;
import com.nuwa.miaosha.user.req.UserAddReq;
import com.nuwa.miaosha.user.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/cache")
@Slf4j
@RefreshScope
public class CacheController {
    @Autowired
    private RedissonUtil redissonUtil;

    @PostMapping("set/{str}")
    public String set(@PathVariable("str") String str) {
        redissonUtil.setStr(str, "123123");
        return redissonUtil.getStr(str);
    }

}
