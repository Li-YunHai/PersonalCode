package com.springboot.notes.servlet;

import org.springframework.boot.web.servlet.ServletRegistrationBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 通过原生Servlet API 自定义Servlet
 * PS：
 *      1、需在主类中添加：@ServletComponentScan开启扫描
 *      2、原生的Servlet没有经过CustomerInterceptor
 *
 * 等价于通过ServletRegistrationBean方式配置
 */
//@WebServlet(urlPatterns = {"/customer/servlet"})
public class CustomerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        resp.getWriter().write("自定义CustomerServlet Demo");
    }
}
