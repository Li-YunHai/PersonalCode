package com.springcloud.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker //Hystrix断路器
public class EurekaProviderMain8002 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaProviderMain8002.class, args);
    }
}
