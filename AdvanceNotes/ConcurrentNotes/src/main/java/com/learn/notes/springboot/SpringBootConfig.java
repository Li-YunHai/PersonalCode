package com.learn.notes.springboot;

import com.learn.notes.spring.dependencies.A;
import com.learn.notes.spring.dependencies.B;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

/**
 * 1、配置类本身会注册到spring容器中
 * 2、配置类中的bean都是单例的，包括其本身
 * 3、proxyBeanMethods
 *      true：full重量级模式，先从容器中获取，没有则创建对象，并加入到容器中
 *      false：lite轻量级模式，总是创建新的对象
 */
@Configuration
public class SpringBootConfig {

    @Bean
    //@ConditionalOnClass(value = {A.class}) //condition条件装配
    //@ConditionalOnBean(name =  {"a", "b"}) //condition条件装配
    @DependsOn(value = {"a", "b"})
    public Car getCar(){
        return new Car();
    }

}
