
package com.springcloud.eureka.controller;

import com.springcloud.eureka.service.FusingHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class FusingHystrixController
{
    @Autowired
    private FusingHystrixService fusingHystrixService;

    @GetMapping("/hystrix/fusing/circuit/{id}")
    public String circuitBreaker(@PathVariable("id") Integer id){
        String result = fusingHystrixService.circuitBreaker(id);
        log.info("****result: "+result);
        return result;
    }
}



