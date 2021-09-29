package com.learn.notes.concurrent;

/**
 * 单例模式在多线程环境下可能存在安全问题
 * 解决方法之一：用synchronized修饰方法getInstance()，但它属重量级同步机制，使用时慎重。
 * 解决方法之二：DCL（Double Check Lock双端检锁机制）
 */
public class SingletonDemo{

    private SingletonDemo(){}

    /**
     * DCL中volatile解析:
     *  原因在于某一个线程执行到第一次检测，读取到的instance不为null时，instance的引用对象可能没有完成初始化。
     *  instance = new SingletonDemo();可以分为以下3步完成(伪代码),不加volatile，高并发时会出现指令重排：
     *          memory = allocate(); //1.分配对象内存空间
     *          instance(memory); //2.初始化对象
     *          instance = memory; //3.设置instance指向刚分配的内存地址，此时instance != null
     */
    private volatile static SingletonDemo instance = null;

    //DCL（Double Check Lock双端检锁机制）
    public static SingletonDemo getInstance() {
        if(instance == null) {
            synchronized(SingletonDemo.class){
                if(instance == null){
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }
}
