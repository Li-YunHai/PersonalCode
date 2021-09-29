package com.springcloud.eureka.service;

import com.springcloud.eureka.service.impl.HystrixFeignFallbackService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 通过fallback指定Hystrix断路器补偿方法
 */
@Component
@FeignClient(value = "CLOUD-EUREKA-PROVIDER"/*, fallback = HystrixFeignFallbackService.class*/)
public interface HystrixFeignService {

    @GetMapping("/hystrix/ok/{id}")
    public String hystrix_OK(@PathVariable("id") Integer id);

    @GetMapping("/hystrix/timeout/{id}")
    public String hystrix_TimeOut(@PathVariable("id") Integer id);

}
