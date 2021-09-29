package com.springboot.starter.service;

import com.springboot.starter.config.HelloProperties;
import org.springframework.beans.factory.annotation.Autowired;

public class HelloService {

    @Autowired
    HelloProperties helloProperties;

    public String sayHello(String name){

        return helloProperties.getPrefix() + ":" + name + "->" + helloProperties.getSuffix();
    }
}
