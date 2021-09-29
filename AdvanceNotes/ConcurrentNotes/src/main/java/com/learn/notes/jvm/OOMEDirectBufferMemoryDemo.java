package com.learn.notes.jvm;

import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

/**
 *
 * 直接内存溢出错误
 *
 * JVM参数：
 * -Xms5m -Xmx5m -XX:MaxDirectMemorySize=5m -XX:+PrintGCDetails
 *
 * 写NIO程序经常使用ByteBuffer来读取或者写入数据，这是一种基于通道(Channel)与缓冲区(Buffer)的IO方式，
 * 它可以使用Native函数库直接分配堆外内存，然后通过一个存储在Java堆里面的DirectByteBuffer对象作为这块内存的引用进行操作。
 * 这样能在一些场景中显著提高性能，因为避兔了在Java堆和Native堆中来回复制数据。
 *
     * ByteBuffer.allocate(capability) 第一种方式是分配JVM堆内存，属于GC管辖范围，由于需要拷贝所以速度相对较慢。
 * ByteBuffer.allocateDirect(capability) 第二种方式是分配OS本地内存，不属于GC管辖范围，由于不需要内存拷贝所以速度相对较快。
 *
 */
public class OOMEDirectBufferMemoryDemo {

    /**
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        System.out.println(String.format("配置的maxDirectMemory: %.2f MB",//
                sun.misc.VM.maxDirectMemory() / 1024.0 / 1024));

        TimeUnit.SECONDS.sleep(3);

        ByteBuffer bb = ByteBuffer.allocateDirect(6 * 1024 * 1024);
    }
}
