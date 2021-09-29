package com.springcloud.rule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerRule {

    @Bean
    public IRule customerRule(){
        return new RoundRobinRule();  //随机算法
        //return new CustomRandomRule();  //自定义负载均衡算法
    }

}





