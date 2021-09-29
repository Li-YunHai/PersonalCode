package com.learn.notes.pattern.proxy.staticProxy;

//被代理对象
public class TeacherDao implements ITeacher {
    @Override
    public void teach() {
        System.out.println("老师授课中...");
    }
}