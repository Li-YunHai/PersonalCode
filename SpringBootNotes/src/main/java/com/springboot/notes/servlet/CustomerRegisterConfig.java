package com.springboot.notes.servlet;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class CustomerRegisterConfig {

    @Bean
    public ServletRegistrationBean customerServlet(){
        CustomerServlet customerServlet = new CustomerServlet();
        return new ServletRegistrationBean(customerServlet,"/customer/servlet");
    }


    @Bean
    public FilterRegistrationBean customerFilter(){
        CustomerFilter customerFilter = new CustomerFilter();
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(customerFilter);
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/customer/servlet"));
        return filterRegistrationBean;
    }

    @Bean
    public ServletListenerRegistrationBean customerListener(){
        CustomerListener customerListener = new CustomerListener();
        return new ServletListenerRegistrationBean(customerListener);
    }
}
