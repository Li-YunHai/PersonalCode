package com.learn.notes.spring.dependencies;

public class B {

    private A a;

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
        System.out.println("B 初始化 setA.");
    }
}
