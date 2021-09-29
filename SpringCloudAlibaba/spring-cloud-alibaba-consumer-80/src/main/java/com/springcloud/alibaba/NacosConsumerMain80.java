package com.springcloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients  //OpenFeign
//指定自定义路由（不能放在SpringApplication扫描目录下）
//@RibbonClient(name="CLOUD-EUREKA-PROVIDER", configuration = CustomerRule.class)
@EnableDiscoveryClient
public class NacosConsumerMain80 {
    public static void main(String[] args) {
        SpringApplication.run(NacosConsumerMain80.class, args);
    }
}
