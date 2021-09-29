package com.springcloud.eureka.service.impl;

import com.springcloud.eureka.service.HystrixFeignService;
import org.springframework.stereotype.Component;

@Component
public class HystrixFeignFallbackService implements HystrixFeignService {

    @Override
    public String hystrix_OK(Integer id) {
        return "80消费端hystrix_OK异常处理信息，请稍后再试，/(ㄒoㄒ)/~~";
    }

    @Override
    public String hystrix_TimeOut(Integer id) {
        return "80消费端hystrix_TimeOut异常处理信息，请稍后再试，/(ㄒoㄒ)/~~";
    }

}
