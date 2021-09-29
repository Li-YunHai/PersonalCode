package com.learn.notes.concurrent.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 抽象队列同步器:
 *      AQS使用一个volatile的int类型的成员变量来表示同步状态，
 *      通过内置的FIFo队列来完成资源获取的排队工作将每条要去抢占资源的线程封装成一个Node，
 *      节点来实现锁的分配，通过CAS完成对State值的修改。它将请求共享资源的线程封装成队列的结点(Node)，
 *      通过CAS、自旋以及LockSupport.park()的方式，维护state变量的状态，使并发达到同步的控制效果。
 *
 * 底层步骤：
 *      1、lock
 *      2、acquire
 *      3、tryAcquire
 *      4、addWaiter(Node.EXCLUSIVE)
 *      5、acquireQueued(addWaiter(Node.EXCLUSIVE), arg)
 *
 * 注意：
 *      1、LCH队列初始化时，会建一个空节点用于占位（傀儡节点、哨兵节点），并不存储任何信息
 */
public class AbstractQueuedSynchronizerDemo {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();

        //带入一个银行办理业务的案例来模拟我们的AQs 如何进行线程的管理和通知唤醒机制
        //3个线程模拟3个来银行网点，受理窗口办理业务的顾客

        //A顾客就是第一个顾客，此时受理窗口没有任何人，A可以直接去办理
        new Thread(()->{
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " come in.");

                try {
                    TimeUnit.SECONDS.sleep(5);//模拟办理业务时间
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                lock.unlock();
            }
        }, "Thread A").start();

        //第2个顾客，第2个线程---->，由于受理业务的窗口只有一个(只能一个线程持有锁)，此代B只能等待，
        //进入候客区
        new Thread(()->{
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " come in.");

            } finally {
                lock.unlock();
            }
        }, "Thread B").start();


        //第3个顾客，第3个线程---->，由于受理业务的窗口只有一个(只能一个线程持有锁)，此代C只能等待，
        //进入候客区
        new Thread(()->{
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " come in.");

            } finally {
                lock.unlock();
            }
        }, "Thread C").start();
    }
}

