package com.learn.notes.concurrent.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * java锁之可重入锁和递归锁
 *      可重入锁（也叫做递归锁）指的是同一线程外层函数获得锁之后，
 *      内层递归函数仍然能获取该锁的代码，在同一个线程在外层方法获取锁的时候，在进入内层方法会自动获取锁。
 *
 * ReentrantLock/synchronized就是一个典型的可重入锁。
 * 可重入锁最大的作用是避免死锁。
 */
public class ReentrantLockDemo {

    public static void main(String[] args) {
        Phone2 phone = new Phone2();

        /**
         * 因为Phone实现了Runnable接口
         */
        Thread t3 = new Thread(phone, "t3");
        Thread t4 = new Thread(phone, "t4");
        t3.start();
        t4.start();
    }
}

class Phone2 implements Runnable{

    Lock lock = new ReentrantLock();
    /**
     * get进去的时候，就加锁，调用set方法的时候，能否访问另外一个加锁的set方法
     */
    public void getLock() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t get Lock");
            setLock();
        } finally {
            lock.unlock();
        }
    }

    public void setLock() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t set Lock");
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {
        getLock();
    }
}
