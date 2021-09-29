package com.learn.notes.concurrent.lock;

import java.util.concurrent.TimeUnit;

/**
 * 死锁编码及定位分析:
 *    死锁是指两个或两个以上的进程在执行过程中，因争夺资源而造成的一种互相等待的现象,
 *    若无外力干涉那它们都将无法推进下去，如果系统资源充足，进程的资源请求都能够碍到满足，
 *    死锁出现的可能性就很低，否则就会因争夺有限的资源而陷入死锁。
 * 发生死锁的四个条件：
 *    1、互斥条件，线程使用的资源至少有一个不能共享的。
 *    2、至少有一个线程必须持有一个资源且正在等待获取一个当前被别的线程持有的资源。
 *    3、资源不能被抢占。
 *    4、循环等待。
 * 查看是否死锁工具：
 *    1、jps命令定位进程号： jps -l
 *    2、jstack找到死锁查看：jstack 进程号
 *    3、jinfo查看进程信息：
 *              查看JVM进程某一参数：jinfo -flag PrintGCDetails 进程号
 *              查看JVM进程所有参数：jinfo -flags 进程号
 *              两个经典参数：
 *                   -Xms等价于-XX:InitialHeapSize，初始大小内存，默认物理内存1/64
 *                   -Xmx等价于-XX:MaxHeapSize，最大分配内存，默认为物理内存1/4
 */
class MyTask implements Runnable{

    private Object resourceA, resourceB;

    public MyTask(Object resourceA, Object resourceB) {
        this.resourceA = resourceA;
        this.resourceB = resourceB;
    }

    @Override
    public void run() {
        synchronized (resourceA) {
            System.out.println(String.format("%s 自己持有%s，尝试持有%s",//
                    Thread.currentThread().getName(), resourceA, resourceB));

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (resourceB) {
                System.out.println(String.format("%s 同时持有%s，%s",//
                        Thread.currentThread().getName(), resourceA, resourceB));
            }
        }
    }
}

public class DeadLockDemo {
    public static void main(String[] args) {
        Object resourceA = new Object();
        Object resourceB = new Object();

        new Thread(new MyTask(resourceA, resourceB),"Thread A").start();
        new Thread(new MyTask(resourceB, resourceA),"Thread B").start();
    }
}
