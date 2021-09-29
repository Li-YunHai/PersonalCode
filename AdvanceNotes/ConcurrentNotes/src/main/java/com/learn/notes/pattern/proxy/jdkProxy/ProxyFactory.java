package com.learn.notes.pattern.proxy.jdkProxy;

import com.learn.notes.pattern.proxy.staticProxy.TeacherDao;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 */
public class ProxyFactory {
    private Object target;//目标对象

    public ProxyFactory(Object target) {
        this.target = target;
    }

    //给目标对象生成一个代理对象
    public Object getProxyInstance() {

        /**
         * newProxyInstance方法参数说明
         * 第一个参数：指定当前目标对象使用的类加载器；
         * 第二个参数：目标对象实现的接口类型；
         * 第三个参数：事件处理，执行目标对象的方式时，会触发事件处理器方法
         */
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("老师备课");
                        //反射机制调用目标对象的方法
                        Object invoke = method.invoke(target, args);
                        System.out.println("老师讲完课了");
                        return invoke;
                    }
                }
        );
    }

    public static void main(String[] args) {
        TeacherDao teacherDao = new TeacherDao();
        ProxyFactory proxyFactory = new ProxyFactory(teacherDao);
        TeacherDao instance = (TeacherDao) proxyFactory.getProxyInstance();
        instance.teach();
    }
}
