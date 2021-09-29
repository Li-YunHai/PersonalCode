package com.learn.notes.pattern.proxy.cglibProxy;

import com.learn.notes.pattern.proxy.staticProxy.TeacherDao;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxyFactory implements MethodInterceptor {
    private Object target;//维护一个目标对象

    //构造器，传入被代理对象
    public CglibProxyFactory(Object target) {
        this.target = target;
    }

    //返回一个代理对象
    public Object getProxyInstance() {
        //1、创建一个工具类
        Enhancer enhancer = new Enhancer();
        //2、设置父类
        enhancer.setSuperclass(target.getClass());
        //3、设置回调函数
        enhancer.setCallback(this);
        //4、创建子类对象，即代理对象
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("老师备课");
        Object invoke = method.invoke(target, args);
        System.out.println("老师讲完课了");
        return invoke;
    }

    public static void main(String[] args) {

        //创建目标对象
        TeacherDao teacherDao = new TeacherDao();
        //创建代理对象，返回被代理的目标对象（必须强制类型转换）
        TeacherDao proxyInstance = (TeacherDao) new CglibProxyFactory(teacherDao).getProxyInstance();
        proxyInstance.teach();
    }
}
