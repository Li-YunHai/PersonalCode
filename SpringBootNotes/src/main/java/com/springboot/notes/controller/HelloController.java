package com.springboot.notes.controller;

import com.springboot.starter.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    HelloService helloService;

    @RequestMapping("/sayHello")
    public String sayHello(String name){
        return helloService.sayHello(name);
    }

}
