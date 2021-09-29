package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("*****插入操作返回结果:" + result);

        if (result > 0) {
            return new CommonResult(200, "插入数据库成功，端口号：" + serverPort, result);
        } else {
            return new CommonResult(444, "插入数据库失败，端口号：" + serverPort, null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("*****查询结果:{}", payment);
        if (payment != null) {
            return new CommonResult(200, "查询成功，端口号：" + serverPort, payment);
        } else {
            return new CommonResult(444, "没有对应记录,查询ID: " + id +"，端口号：" + serverPort, null);
        }
    }

    @GetMapping(value = "/payment/discovery")
    public CommonResult<Object> discovery(){
        //获取服务列表信息
        List<String> servicesList = discoveryClient.getServices();
        servicesList.stream().forEach((x) -> {
            log.info("*****service:{}", x);
        });

        //根据微服务具体的服务名称进一步获取微服务信息
        List<ServiceInstance> instanceList = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        instanceList.stream().forEach((x) -> {
            log.info("服务名称:{}，服务地址:{}，服务端口:{}，服务URL:{}", x.getInstanceId(),
                    x.getHost(), x.getPort(), x.getUri());
        });
        return new CommonResult(200, "查询成功，端口号：" + serverPort, discoveryClient);
    }

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeOut()
    {
        System.out.println("*****paymentFeignTimeOut from port: "+serverPort);
        //暂停几秒钟线程
        try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
        return serverPort;
    }
}



