
package com.springcloud.eureka.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class FusingHystrixService {

    //服务熔断
    @HystrixCommand(fallbackMethod = "circuitBreakerFallback", commandProperties = {
            //是否启用断路器
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            // 如果滚动时间窗口（默认10秒,数量20）内仅收到了19个请求， 即使这19个请求都失败了，断路器也不会打开。
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            // 该属性用来设置当断路器打开之后的休眠时间窗。 休眠时间窗结束之后，
            // 会将断路器置为 "半开" 状态，尝试熔断的请求命令，如果依然失败就将断路器继续设置为"打开"状态，
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            //如果错误请求数的百分比超过60,就把断路器设置为 "打开" 状态，否则就设置为"关闭"状态,默认：50%。
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),
    })
    public String circuitBreaker(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("******id 不能负数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName() + "\t" + "调用成功，流水号: " + serialNumber;
    }

    public String circuitBreakerFallback(@PathVariable("id") Integer id) {
        return "id 不能负数，请稍后再试，/(ㄒoㄒ)/~~   id: " + id;
    }
}



