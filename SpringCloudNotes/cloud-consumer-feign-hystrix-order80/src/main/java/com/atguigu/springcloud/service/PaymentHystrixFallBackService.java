package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentHystrixFallBackService implements PaymentHystrixService{

    @Override
    public String paymentInfo_OK(Integer id) {
        return "80消费端paymentInfo_OK异常处理信息，请稍后再试，/(ㄒoㄒ)/~~";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "80消费端paymentInfo_TimeOut异常处理信息，请稍后再试，/(ㄒoㄒ)/~~";
    }
}
