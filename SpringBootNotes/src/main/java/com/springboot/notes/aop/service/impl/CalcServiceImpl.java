package com.springboot.notes.aop.service.impl;


import com.springboot.notes.aop.service.CalcService;
import org.springframework.stereotype.Service;

@Service
public class CalcServiceImpl implements CalcService {

    @Override
    public int div(int x, int y) {
        int result = x / y;
        System.out.println("===>CalcServiceImpl被调用，计算结果为：" + result);
        return result;
    }
}

