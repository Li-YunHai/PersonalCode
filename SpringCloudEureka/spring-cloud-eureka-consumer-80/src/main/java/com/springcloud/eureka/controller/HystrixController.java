package com.springcloud.eureka.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.springcloud.eureka.service.HystrixFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Slf4j
@RestController
public class HystrixController {

    @Autowired
    HystrixFeignService hystrixFeignService;

    /**
     * 测试路径：http://localhost/consumer/hystrix/ok/1
     * @return
     */
    @GetMapping("/consumer/hystrix/ok/{id}")
    public String hystrix_OK(@PathVariable("id") Integer id) throws InterruptedException {
        return hystrixFeignService.hystrix_OK(id);
    }

    /**
     * 测试路径：http://localhost/consumer/hystrix/timeout/1
     * @return
     */
    @HystrixCommand(fallbackMethod = "hystrix_TimeOutFallbackMethod", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "6000")
    })
    @GetMapping("/consumer/hystrix/timeout/{id}")
    public String hystrix_TimeOut(@PathVariable("id") Integer id) {
        log.info("消费者80,开始时间：" + LocalDateTime.now().toString());
        //int x = 10/0;
        String result = hystrixFeignService.hystrix_TimeOut(id);
        log.info("消费者80,结束时间：" + LocalDateTime.now().toString());
        return result;
    }

    public String hystrix_TimeOutFallbackMethod(@PathVariable("id") Integer id) {
        log.info("消费者80,结束时间：" + LocalDateTime.now().toString());
        return "我是消费者80,对方支付系统繁忙请10秒钟后再试或者自己运行出错请检查自己,o(╥﹏╥)o";
    }

}
