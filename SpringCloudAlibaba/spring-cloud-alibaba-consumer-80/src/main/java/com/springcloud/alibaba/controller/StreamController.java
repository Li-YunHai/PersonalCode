package com.springcloud.alibaba.controller;

import com.springcloud.alibaba.service.StreamSendMessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * SpringCloud Stream 屏蔽底层消息中间件的差异,降低切换成本，统一消息的编程模型
 * SpringCloud Stream 为一些供应商的消息中间件产品提供了个性化的自动化配置实现，引用了发布-订阅、消费组、分区的三个核心概念。
 * Spring Cloud Stream标准流程套路:
 *    1、Binder：很方便的连接中间件，屏蔽差异，INPUT对应于消费者，OUTPUT对应于生产者
 *    2、Channel：通道，是队列Queue的一种抽象，在消息通讯系统中就是实现存储和转发的媒介，通过Channel对队列进行配置
 *    3、Source、Sink：简单的可理解为参照对象是Spring Cloud Stream自身，从Stream发布消息就是输出，接受消息就是输入。
 */
@RestController
public class StreamController {
    @Resource
    private StreamSendMessageProvider streamSendMessageProvider;

    @GetMapping(value = "/sendMessage")
    public String sendMessage() {
        return streamSendMessageProvider.send();
    }
}




