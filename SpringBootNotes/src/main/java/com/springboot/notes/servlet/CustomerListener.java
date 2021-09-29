package com.springboot.notes.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 通过原生Servlet API 自定义ServletContextListener
 * PS：
 *      1、需在主类中添加：@ServletComponentScan开启扫描
 *
 * 等价于通过ServletRegistrationBean方式配置
 */
@Slf4j
//@WebListener
public class CustomerListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("自定义CustomerListener监听到项目启动完成");
        ServletContextListener.super.contextInitialized(sce);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("自定义CustomerListener监听到项目销毁");
        ServletContextListener.super.contextDestroyed(sce);
    }
}
