package com.nuwa.miaosha.ms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(scanBasePackages = {"com.nuwa.miaosha","com.nuwa.miaosha.common"})
@MapperScan("com.nuwa.miaosha.ms.mapper")
@EnableAsync
public class MsApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsApplication.class, args);
    }
}
