package com.springboot.notes.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * spring 4
 *      我是环绕通知之前AAA
 *      ********@Before我是前置通知
 *      ===>CalcServiceImpl被调用，计算结果为：5
 *      我是环绕通知之后BBB
 *      ********@After我是后置通知
 *      ********@AfterReturning我是返回后通知
 *
 * spring5
 *      我是环绕通知之前AAA
 *      ********@Before我是前置通知
 *      ===>CalcServiceImpl被调用，计算结果为：5
 *      ********@AfterReturning我是返回后通知
 *      ********@After我是后置通知
 *      我是环绕通知之后BBB
 */
@Aspect
@Component
public class MyAspect {

    /**
     * 前置通知：目标方法之前执行
     */
    @Before("execution(public int com.springboot.notes.aop.service.impl.CalcServiceImpl.*(..))")
    public void beforeNotify() {
        System.out.println("********@Before我是前置通知");
    }

    /**
     * 后置通知：目标方法之后执行（始终执行)
     */
    @After("execution(public int com.springboot.notes.aop.service.impl.CalcServiceImpl.*(..))")
    public void afterNotify() {
        System.out.println("********@After我是后置通知");
    }

    /**
     * 返回后通知：执行方法结束前执行(异常不执行)
     */
    @AfterReturning("execution(public int com.springboot.notes.aop.service.impl.CalcServiceImpl.*(..))")
    public void afterReturningNotify() {
        System.out.println("********@AfterReturning我是返回后通知");
    }

    /**
     * 异常通知：出现异常时候执行
     */
    @AfterThrowing(" execution(public int com.springboot.notes.aop.service.impl.CalcServiceImpl.*(..))")
    public void afterThrowingNotify() {
        System.out.println("********@AfterThrowing我是异常通知");
    }

    /**
     * 环绕通知：环绕目标方法执行
     */
    @Around(" execution(public int com.springboot.notes.aop.service.impl.CalcServiceImpl.*(..))")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object retvalue = null;
        System.out.println("我是环绕通知之前AAA");
        retvalue = proceedingJoinPoint.proceed();
        System.out.println("我是环绕通知之后BBB");
        return retvalue ;
    }
}
