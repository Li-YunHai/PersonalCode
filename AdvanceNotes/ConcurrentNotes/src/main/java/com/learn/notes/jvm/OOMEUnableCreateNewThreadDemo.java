package com.learn.notes.jvm;

import java.util.concurrent.TimeUnit;

/**
 * 不能够创建更多的新的线程了，也就是说创建线程的上限达到了
 * 高并发请求服务器时，经常会出现异常java.lang.OutOfMemoryError:unable to create new native thread，准确说该native thread异常与对应的平台有关
 *
 * 导致原因：
 *      1、应用创建了太多线程，一个应用进程创建多个线程，超过系统承载极限
 *      2、服务器并不允许你的应用程序创建这么多线程，linux系统默认运行单个进程可以创建的线程为1024个，如果应用创建超过这个数量，就会报 java.lang.OutOfMemoryError:unable to create new native thread
 *
 * 解决方法：
 *      1、想办法降低你应用程序创建线程的数量，分析应用是否真的需要创建这么多线程，如果不是，改代码将线程数降到最低
 *      2、对于有的应用，确实需要创建很多线程，远超过linux系统默认1024个线程限制，可以通过修改Linux服务器配置，扩大linux默认限制
 *
 * OOM之unable to create new native thread上限调整
 *      查看系统线程限制数目：ulimit -u
 *      修改系统线程限制数目：vim /etc/security/limits.d/90-nproc.conf
 *
 */
public class OOMEUnableCreateNewThreadDemo {
    public static void main(String[] args) {
        for (int i = 0; ; i++) {
            System.out.println("************** i = " + i);
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}

