package com.nuwa.miaosha.ms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(scanBasePackages = {"com.nuwa.miaosha","com.nuwa.miaosha.common"})
@EnableAsync
@EnableFeignClients(basePackages = {"com.nuwa.miaosha"})
public class MsApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsApplication.class, args);
    }
}
