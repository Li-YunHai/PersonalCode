package com.springboot.notes.model;

import lombok.Data;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Data
public class Dog {

    String name;

    int age;

    public Dog() {
        System.out.println("实例化Dog.....");
    }

    @PostConstruct  //@PostConstruct对象装配完成后执行
    public void initMethod(){
        System.out.println("初始化Dog.....");
    }

    @PreDestroy //@PreDestroy对象销毁前执行
    public void destroyMethod(){
        System.out.println("销毁Dog.....");
    }
}
