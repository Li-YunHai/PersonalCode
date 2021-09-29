package com.learn.notes.concurrent.lock;

/**
 * Object类中的wait和notify方法实现线程等待和唤醒
 * wait和notify方法必须要在同步块或者方法里面且成对出现使用，否则会抛出java.lang.IllegalMonitorStateException。
 */
public class WaitNotifyDemo {

    static Object lock = new Object();

    public static void main(String[] args) {
        new Thread(()->{
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName()+" come in.");
                try {
                    lock.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+" 换醒.");
        }, "Thread A").start();

        new Thread(()->{
            synchronized (lock) {
                lock.notify();
                System.out.println(Thread.currentThread().getName()+" 通知.");
            }
        }, "Thread B").start();
    }
}
