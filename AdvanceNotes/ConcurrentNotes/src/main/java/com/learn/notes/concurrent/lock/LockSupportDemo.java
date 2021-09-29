package com.learn.notes.concurrent.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport是用来创建锁和其他同步类的基本线程阻塞原语。
 * LockSupport中的park()和 unpark()的作用分别是阻塞线程和解除阻塞线程。
 * LockSupport类使用了一种名为Permit（许可）的概念来做到阻塞和唤醒线程的功能，每个线程都有一个许可（permit），permit只有两个值1和零，默认是零。
 *
 * 3种让线程等待和唤醒的方法:
 *      1、使用Object中的wait()方法让线程等待，使用object中的notify()方法唤醒线程
 *      2、使用JUC包中Condition的await()方法让线程等待，使用signal()方法唤醒线程
 *      3、LockSupport类可以阻塞当前线程以及唤醒指定被阻塞的线程
 */
public class LockSupportDemo {

    public static void main(String[] args) {
        Thread a = new Thread(()->{
//			try {
//				TimeUnit.SECONDS.sleep(2);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
            System.out.println(Thread.currentThread().getName() + " come in. " + System.currentTimeMillis());
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + " 换醒. " + System.currentTimeMillis());
        }, "Thread A");
        a.start();

        Thread b = new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LockSupport.unpark(a);
            System.out.println(Thread.currentThread().getName()+" 通知.");
        }, "Thread B");
        b.start();
    }

}

