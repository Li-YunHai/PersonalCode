package com.springcloud.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GateWayMain9527 {

    /**
     * SpringCloud Gateway 使用的Webflux中的reactor-netty响应式编程组件，底层使用了Netty通讯框架。
     * 三大核心概念
     *   1、Route(路由)：路由是构建网关的基本模块，它由ID，目标URI，一系列的断言和过滤器组成，如果断言为true则匹配该路由
     *   2、Predicate(断言)：参考的是Java8的java.util.function.Predicate 开发人员可以匹配HTTP请求中的所有内容(例如请求头或请求参数)，如果请求与断言相匹配则进行路由
     *   3、Filter(过滤)：指的是Spring框架中GatewayFilter的实例，使用过滤器，可以在请求被路由前或者之后对请求进行修改。
     *
     * 断言：默认支持9种断言
     * 过滤：
     *   1、生命周期：pre，post
     *   2、种类：GatewayFilter（31种）、GlobalFilter（6种）
     */
    public static void main(String[] args) {
        SpringApplication.run(GateWayMain9527.class, args);
    }
}



