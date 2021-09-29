package com.springboot.notes.aop.test;


import com.springboot.notes.aop.service.CalcService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private CalcService calcService;

    @Test
    public void testAop() {
        System.out.println(String.format("Spring Verision : %s, Sring Boot Version : %s.", //
                SpringVersion.getVersion(), SpringBootVersion.getVersion()));

        calcService.div(10, 2);
    }
}

