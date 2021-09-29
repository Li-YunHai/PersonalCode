package com.learn.notes.concurrent;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class VolatileDemo {
    /**
     * 假设是主物理内存
     */
    class MyData {

        int number = 0;
        //volatile int number = 0;

        AtomicInteger number2 = new AtomicInteger(0);

        boolean flag = false;

        //可见性
        public void addTo60() {
            this.number = 60;
        }
        //原子性
        public void addPlusPlus() {
            number ++;
            number2.getAndIncrement();
        }
        //指令重排
        public void method01(){
            number = 1;//语句1
            flag = true;
        }
        public void method02(){
            if(flag == true){
                number = number + 5;
                System.out.println("retValue: " + number);//可能是6或1或5或0
            }
        }
    }

    /**
     * 验证volatile的可见性
     * 1. 假设int number = 0， number变量之前没有添加volatile关键字修饰
     */
    @Test
    public void testVisibility(){
        //资源类
        MyData myData = new MyData();

        // AAA线程 实现了Runnable接口的，lambda表达式
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "\t 可见性验证开始");

                // 线程睡眠3秒，假设在进行运算
                TimeUnit.SECONDS.sleep(3);

                // 修改number的值
                myData.addTo60();

                // 输出修改后的值
                System.out.println(Thread.currentThread().getName() + "\t 可见性验证结束:" + myData.number);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "AAA").start();

        // main线程就一直在这里等待循环，直到number的值不等于零
        while(myData.number == 0) {}

        // 按道理这个值是不可能打印出来的，因为主线程运行的时候，number的值为0，所以一直在循环
        // 如果能输出这句话，说明AAA线程在睡眠3秒后，更新的number的值，重新写入到主内存，并被main线程感知到了
        System.out.println(Thread.currentThread().getName() + "\t 主线程执行结束");
    }

    /**
     * volatile不保证原子性案例：
     *  解决方法：
     *      1、加synchronize同步锁
     *      2、使用原子类：AtomicInteger
     */
    @Test
    public void testAtomicity(){
        //资源类
        MyData myData = new MyData();

        // 创建10个线程，线程里面进行1000次循环
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                // 里面
                for (int j = 0; j < 1000; j++) {
                    myData.addPlusPlus();
                }
            }, String.valueOf(i)).start();
        }

        // 需要等待上面20个线程都计算完成后，在用main线程取得最终的结果值
        // 这里判断线程数是否大于2，为什么是2？因为默认是有两个线程的，一个main线程，一个gc线程
        while(Thread.activeCount() > 2) {
            // yield表示不执行
            Thread.yield();
        }

        // 查看最终的值
        // 假设volatile保证原子性，那么输出的值应该为：  20 * 1000 = 20000
        System.out.println(Thread.currentThread().getName() + "\t 不保证原子性: " + myData.number);
        System.out.println(Thread.currentThread().getName() + "\t 保证原子性:" + myData.number2);

    }

    /**
     * 指令重排案例：
     */
    @Test
    public void reSortSeq(){
        //资源类
        MyData myData = new MyData();
        new Thread(() -> {
            myData.method01();
        }).start();
        new Thread(() -> {
            myData.method02();
        }).start();
    }
}


