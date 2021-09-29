package com.learn.notes.jvm;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 *
 * Java 8及之后的版本使用Metaspace来替代永久代。
 *
 * JVM参数：
 * -Xms5m -Xmx5m -XX:MetaspaceSize=8m -XX:MaxMetaspaceSize=8m -XX:+PrintGCDetails
 *
 * Metaspace是方法区在Hotspot 中的实现，它与持久代最大的区别在于：Metaspace并不在虚拟机内存中而是使用本地内存也即在Java8中,
 * classe metadata(the virtual machines internal presentation of Java class)，被存储在叫做Metaspace native memory。
 *
 * 永久代（Java8后被原空向Metaspace取代了）存放了以下信息：
 *      1、虚拟机加载的类信息
 *      2、常量池
 *      3、静态变量
 *      4、即时编译后的代码
 * 模拟Metaspace空间溢出，我们借助CGLib直接操作字节码运行时不断生成类往元空间灌，类占据的空间总是会超过Metaspace指定的空间大小的。
 *
 */
public class OOMEMetaspaceDemo {
    // 静态类
    static class OOMObject {}

    /**
     * -XX:MetaspaceSize=10m -XX:MaxMetaspaceSize=10m
     *
     * @param args
     */
    public static void main(final String[] args) {
        // 模拟计数多少次以后发生异常
        int i =0;
        try {
            while (true) {
                i++;
                // 使用Spring的动态字节码技术
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(OOMObject.class);
                enhancer.setUseCache(false);
                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        return methodProxy.invokeSuper(o, args);
                    }
                });
                enhancer.create();
            }
        } catch (Throwable e) {
            System.out.println("发生异常的次数:" + i);
            e.printStackTrace();
        } finally {

        }

    }
}
