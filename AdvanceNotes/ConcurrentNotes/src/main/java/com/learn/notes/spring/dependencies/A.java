package com.learn.notes.spring.dependencies;

public class A {
    private B b;

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
        System.out.println("A 初始化 setB.");
    }
}
