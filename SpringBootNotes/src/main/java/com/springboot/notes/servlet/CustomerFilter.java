package com.springboot.notes.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 通过原生Servlet API 自定义Filter
 * PS：
 *      1、需在主类中添加：@ServletComponentScan开启扫描
 *
 * 等价于通过ServletRegistrationBean方式配置
 */
@Slf4j
//@WebFilter(urlPatterns = {"/css/*","/fonts/*","/images/*","/js/*"})
public class CustomerFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("自定义CustomerFilter 初始化");
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("自定义CustomerFilter 处理用户请求");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        log.info("自定义CustomerFilter 销毁");
        Filter.super.destroy();
    }
}
