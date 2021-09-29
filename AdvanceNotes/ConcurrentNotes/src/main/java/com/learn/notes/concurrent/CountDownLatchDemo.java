package com.learn.notes.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * 让一线程阻塞直到另一些线程完成一系列操作才被唤醒。
 * 当一个或多个线程调用await()时，调用线程会被阻塞。其它线程调用countDown()会将计数器减1(调用countDown方法的线程不会阻塞)，
 * 当计数器的值变为零时，因调用await方法被阻塞的线程会被唤醒，继续执行。
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {

        // 计数器
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 0; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 上完自习，离开教室");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }

        countDownLatch.await();

        System.out.println(Thread.currentThread().getName() + "\t 班长最后关门");
    }

}
