package com.nuwa.miaosha.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 用户服务启动类
 */
@SpringBootApplication(scanBasePackages = {"com.nuwa.miaosha","com.nuwa.miaosha.common"})
@MapperScan("com.nuwa.miaosha.user.mapper")
@EnableAsync
public class UseApplication {
    public static void main(String[] args) {
        SpringApplication.run(UseApplication.class,args);
    }
}
