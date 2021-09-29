package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {

    //单机模式
    public static final String PAYMENTSRV_URL = "http://cloud-provider-payment";
    //集群模式
    //public static final String PaymentSrv_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "consumer/payment/zk")
    public String paymentZk(){
        return restTemplate.getForObject(PAYMENTSRV_URL + "/payment/zk", String.class);
    }

}
