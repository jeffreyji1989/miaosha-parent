package com.nuwa.miaosha.good;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(scanBasePackages = {"com.nuwa"})
@EnableAsync
//@EnableDiscoveryClient
public class GoodApplication {
    public static void main(String[] args) {
        SpringApplication.run(GoodApplication.class, args);
    }
}
