package com.learn.notes.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 超出GC开销限制: GC overhead limit exceeded
 *
 * JVM参数：
 * -Xms5m -Xmx5m -XX:MaxDirectMemorySize=5m -XX:+PrintGCDetails
 *
 * GC回收时间过长时会抛出OutOfMemroyError。
 * 过长的定义是：超过98%的时间用来做GC并且回收了不到2%的堆内存，连续多次GC 都只回收了不到2%的极端情况下才会抛出。
 * GC清理的这么点内存很快会再次填满，迫使cc再次执行。这样就形成恶性循环，CPU使用率一直是100%，而Gc却没有任何成果。
 */
public class OOMEGCOverheadLimitExceededDemo {

    /**
     * @param args
     */
    public static void main(String[] args) {
        int i = 0;
        List<String> list = new ArrayList<>();
        try {
            while(true) {
                list.add(String.valueOf(++i).intern());
            }
        } catch (Exception e) {
            System.out.println("***************i:" + i);
            e.printStackTrace();
            throw e;
        }
    }

}
