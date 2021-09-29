
package com.springcloud.eureka.service;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@DefaultProperties(defaultFallback = "hystrix_Global_FallbackMethod")
public class HystrixService {
    /**
     * 正常访问，一切OK
     */
    @HystrixCommand  //不加这个注释，不会关联：@DefaultProperties
    public String hystrix_OK(Integer id) {
        return "线程池:" + Thread.currentThread().getName() + "Hystrix_OK,id: " + id + "\t" + "O(∩_∩)O";
    }

    /**
     * 超时访问，演示降级
     */
    @HystrixCommand(fallbackMethod = "hystrix_TimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String hystrix_TimeOut(Integer id) {
        int second = 2000;
        //int x = 10 / 0;
        try {
            TimeUnit.MILLISECONDS.sleep(second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池:" + Thread.currentThread().getName() + "Hystrix_TimeOut,id: " + id + "\t" + "O(∩_∩)O，耗费毫秒: " + second;
    }

    public String hystrix_TimeOutHandler(Integer id) {
        return "8001客户端超时或异常：\t" + "\t当前线程池名字" + Thread.currentThread().getName();
    }

    public String hystrix_Global_FallbackMethod() {
        return "8001客户端Global异常处理信息，请稍后再试，/(ㄒoㄒ)/~~";
    }

}



