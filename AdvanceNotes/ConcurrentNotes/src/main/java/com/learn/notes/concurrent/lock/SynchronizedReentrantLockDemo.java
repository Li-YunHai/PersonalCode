package com.learn.notes.concurrent.lock;

/**
 * java锁之可重入锁和递归锁
 *      可重入锁（也叫做递归锁）指的是同一线程外层函数获得锁之后，
 *      内层递归函数仍然能获取该锁的代码，在同一个线程在外层方法获取锁的时候，在进入内层方法会自动获取锁。
 *
 * ReentrantLock/synchronized就是一个典型的可重入锁。
 * 可重入锁最大的作用是避免死锁。
 */
public class SynchronizedReentrantLockDemo {

    public static void main(String[] args) {
        Phone phone = new Phone();

        // 两个线程操作资源列
        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t1").start();

        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t2").start();
    }

}

class Phone {

    public synchronized void sendSMS() throws Exception{
        System.out.println(Thread.currentThread().getName() + "\t invoked sendSMS()");

        // 在同步方法中，调用另外一个同步方法
        sendEmail();
    }

    public synchronized void sendEmail() throws Exception{
        System.out.println(Thread.currentThread().getId() + "\t invoked sendEmail()");
    }
}