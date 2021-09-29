
package com.springcloud.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer //配置中心
public class ConfigCenterMain3344 {
    /**
     * *******  SpringCloud Config  *******
     *
     *  SpringCloud Config分为服务端和客户端两部分。
     *  SpringCloud Config为微服务架构中的微服务提供集中化的外部配置支持，配置服务器为各个不同微服务应用的所有环境提供了一个中心化的外部配置。
     *  服务端：
     *       服务端也称为分布式配置中心，它是一个独立的微服务应用，用来连接配置服务器并为客户端提供获取配置信息，加密/解密信息等访问接口
     *  客户端：
     *       客户端则是通过指定的配置中心来管理应用资源，以及与业务相关的配置内容，并在启动的时候从配置中心获取和加载配置信息配置服务器
     *       默认采用git来存储配置信息，这样就有助于对环境配置进行版本管理，并且可以通过git客户端工具来方便的管理和访问配置内容。
     *
     *  用户级资源配置项：applicaiton.yml
     *  系统级资源配置项：bootstrap.yml，优先级更加高
     *
     *  配置读取规则：
     *   1、/{label}/{application}-{profile}.yml
     *   2、/{application}-{profile}.yml  默认：master分支
     *   3、/{application}/{profile}[/{label}]  默认：master分支
     *
     *  客户端刷新：curl -X POST "http://localhost:8001/actuator/refresh"
     *
     *
     * *******  SpringCloud Config  *******
     *
     *
     * *******  SpringCloud Bus  *******
     *
     * Spring Cloud Bus是用来将分布式系统的节点与轻量级消息系统链接起来的框架，它整合了Java的事件处理机制和消息中间件的功能。
     * Spring Cloud Bus能管理和传播分布式系统间的消息，就像一个分布式执行器，可用于广播状态更改、事件推送等，也可以当作微服务间的通信通道。
     * Spring Clud Bus目前支持RabbitMQ和Kafka。
     *
     * 基本原理:
     *      ConfigClient实例都监听MQ中同一个topic(默认是springCloudBus)。当一个服务刷新数据的时候，
     *      它会把这个信息放入到Topic中，这样其它监听同一Topic的服务就能得到通知，然后去更新自身的配置。
     *
     * 全局刷新：curl -X POST "http://localhost:3344/actuator/bus-refresh"
     * 定点刷新：curl -X POST "http://localhost:3344/actuator/bus-refresh/cloud-eureka-provider:8001"
     * *******  SpringCloud Bus  *******
     */
    public static void main(String[] args) {
        SpringApplication.run(ConfigCenterMain3344.class, args);
    }
}