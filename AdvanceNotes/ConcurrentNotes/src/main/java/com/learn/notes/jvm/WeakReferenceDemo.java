package com.learn.notes.jvm;

import java.lang.ref.WeakReference;

/**
 * 弱引用需要用java.lang.ref.WeakReference类来实现，它比软引用的生存期更短，
 * 对于只有弱引用的对象来说，只要垃圾回收机制一运行不管JVM的内存空间是否足够，都会回收该对象占用的内存。
 *
 * 场景：假如有一个应用需要读取大量的本地图片
 *      如果每次读取图片都从硬盘读取则会严重影响性能
 *      如果一次性全部加载到内存中，又可能造成内存溢出
 */
public class WeakReferenceDemo {
    public static void main(String[] args) {
        Object o1 = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(o1);
        System.out.println(o1);
        System.out.println(weakReference.get());
        o1 = null;
        System.gc();
        System.out.println(o1);
        System.out.println(weakReference.get());
    }
}

