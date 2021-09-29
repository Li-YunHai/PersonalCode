package com.learn.notes.spring.aop.test;

import com.learn.notes.spring.aop.service.CalcService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.SpringVersion;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@EnableAspectJAutoProxy
@RunWith(SpringRunner.class)
public class AopTest {

    @Resource
    private CalcService calcService;

    @Test
    public void testAop4() {
        System.out.println(String.format("Spring Verision : %s, Sring Boot Version : %s.", //
                SpringVersion.getVersion(), SpringBootVersion.getVersion()));

        calcService.div(10, 2);
    }
}

