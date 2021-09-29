package com.learn.notes.jvm;

/**
 *
 * 堆内存溢出
 *
 * JVM参数：
 * -Xms10m -Xmx10m -XX:+PrintGCDetails
 */
public class OOMEJavaHeapSpaceDemo {

    /**
     * 创建一个超出堆内存的对象
     * @param args
     */
    public static void main(String[] args) {
        byte[] array = new byte[80 * 1024 * 1024];
    }

}
