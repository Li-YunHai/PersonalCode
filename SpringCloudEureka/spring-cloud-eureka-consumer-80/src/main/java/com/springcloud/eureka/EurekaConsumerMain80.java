package com.springcloud.eureka;

import com.springcloud.rule.CustomerRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient  //Eureka客户端
@EnableFeignClients  //OpenFeign
@EnableHystrix  //Hystrix
//指定自定义路由（不能放在SpringApplication扫描目录下）
//@RibbonClient(name="CLOUD-EUREKA-PROVIDER", configuration = CustomerRule.class)
@EnableDiscoveryClient
public class EurekaConsumerMain80 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaConsumerMain80.class, args);
    }
}
