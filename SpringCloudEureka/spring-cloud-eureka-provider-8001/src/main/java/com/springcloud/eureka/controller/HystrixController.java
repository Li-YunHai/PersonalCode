
package com.springcloud.eureka.controller;

import com.springcloud.eureka.service.HystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HystrixController
{
    @Autowired
    private HystrixService hystrixService;

    @GetMapping("/hystrix/ok/{id}")
    public String hystrix_OK(@PathVariable("id") Integer id) throws InterruptedException
    {
        String result = hystrixService.hystrix_OK(id);
        log.info("****result: "+result);
        return result;
    }

    @GetMapping("/hystrix/timeout/{id}")
    public String hystrix_TimeOut(@PathVariable("id") Integer id)
    {
        String result = hystrixService.hystrix_TimeOut(id);
        log.info("****result: "+result);
        return result;
    }

}



