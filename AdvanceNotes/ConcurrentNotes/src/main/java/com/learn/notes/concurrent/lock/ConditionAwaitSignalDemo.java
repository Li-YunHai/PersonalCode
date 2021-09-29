package com.learn.notes.concurrent.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition接口中的await后signal方法实现线程的等待和唤醒，
 * 与Object类中的wait和notify方法实现线程等待和唤醒类似。
 * wait和notify方法必须要在同步块或者方法里面且成对出现使用，否则会抛出java.lang.IllegalMonitorStateException。
 */
public class ConditionAwaitSignalDemo {

    public static void main(String[] args) {

        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+" come in.");
                lock.lock();
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

            System.out.println(Thread.currentThread().getName()+" 被唤醒.");
        },"Thread A").start();

        new Thread(()->{
            try {
                lock.lock();
                condition.signal();
                System.out.println(Thread.currentThread().getName()+" 通知.");
            }finally {
                lock.unlock();
            }
        },"Thread B").start();
    }

}

