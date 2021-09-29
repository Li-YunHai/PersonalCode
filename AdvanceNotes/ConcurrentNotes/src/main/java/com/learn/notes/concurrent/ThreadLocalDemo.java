package com.learn.notes.concurrent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalDemo {

    public static final int CONCURRENT = 1000;

    static volatile ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>(){
        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println(this.toString() + "is gc !");
        }
    };

    static volatile CountDownLatch latch = new CountDownLatch(CONCURRENT);

    public static class ParseDate implements Runnable {
        int i = 0;

        public ParseDate(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            try {
                if (threadLocal.get() == null) {
                    threadLocal.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") {
                        @Override
                        protected void finalize() throws Throwable {
                            super.finalize();
                            System.out.println(this.toString() + " is gc !");
                        }
                    });
                    System.out.println(Thread.currentThread().getId() + " created SimpleDateFormat ! ");
                }
                Date parseDate = threadLocal.get().parse("2018-07-25 23:00:00");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                latch.countDown();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            executorService.execute(new ParseDate(i));
        }
        latch.await();
        System.out.println(" mission complete ！");
        threadLocal = null;
        System.gc();
        System.out.println(" first gc is complete ! ");

        threadLocal = new ThreadLocal<SimpleDateFormat>();
        latch = new CountDownLatch(CONCURRENT);
        for (int i = 0; i < 1000; i++) {
            executorService.execute(new ParseDate(i));
        }
        latch.await();
        Thread.sleep(1000);
        System.gc();
        System.out.println(" second gc is complete ！ ");
    }

}
