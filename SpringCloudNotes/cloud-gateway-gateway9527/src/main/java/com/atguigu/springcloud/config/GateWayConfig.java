package com.atguigu.springcloud.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class GateWayConfig {

    /**
     * 配置了一个id为route-name的路由规则，
     * 当访问地址 http://localhost:9527/guonei时会自动转发到地址：http://news.baidu.com/guonei
     * @param builder
     * @return
     */
//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//        RouteLocatorBuilder.Builder routes = builder.routes();
//        routes.route("path_route_atguigu1", r -> (r.path("/guonei").uri("http://news.baidu.com/guonei"))).build();
//        routes.route("path_route_atguigu2", r -> (r.path("/guoji").uri("http://news.baidu.com/guoji"))).build();
//        return routes.build();
//    }
}
