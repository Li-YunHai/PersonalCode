package com.springboot.notes.config;

import com.springboot.notes.converter.CustomerMessageConverter;
import com.springboot.notes.interceptor.CustomerInterceptor;
import com.springboot.notes.model.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;
import org.springframework.web.accept.ParameterContentNegotiationStrategy;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.util.UrlPathHelper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration(proxyBeanMethods = false)
public class WebMvcConfig implements WebMvcConfigurer {

/*
    //增加访问路径
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
    //请求与页面映射
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //urlPath:请求
        //setViewName:html页面
        //registry.addViewController("/");
    }
*/

    //自定义RestFull请求风格filter
    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter(){
        HiddenHttpMethodFilter methodFilter = new HiddenHttpMethodFilter();
        methodFilter.setMethodParam("_method");
        return methodFilter;
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        WebMvcConfigurer.super.configurePathMatch(configurer);
        UrlPathHelper urlPathHelper = new UrlPathHelper();
        urlPathHelper.setAlwaysUseFullPath(true);
        urlPathHelper.setRemoveSemicolonContent(false); //是否移除URL中";"后面的内容,矩阵变量
        configurer.setUrlPathHelper(urlPathHelper);
    }

    /**
     * 自定义参数转换器
     * @param registry
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new Converter<String, Car>() {

            @Override
            public Car convert(String source) {
                // BYD,100000
                if(!StringUtils.isEmpty(source)){
                    Car car = new Car();
                    String[] split = source.split(",");
                    car.setBrand(split[0]);
                    car.setPrice(Long.parseLong(split[1]));
                    return car;
                }
                return null;
            }
        });
    }

    /**
     * 扩展自定义的MessageConverters
     * 测试：可以将请求头中的Accept调整为：application/x-guigu
     * @param converters
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        WebMvcConfigurer.super.extendMessageConverters(converters);
        //将自定义的MessageConverters添加到系统中
        //MediaType.parseMediaTypes("application/x-guigu");
        converters.add(new CustomerMessageConverter());
    }

    /**
     * 自定义内容协商策略
     * 测试:
     *      http://localhost:8080/test/person?format=json   默认
     *      http://localhost:8080/test/person?format=xml    默认
     *      http://localhost:8080/test/person?format=x-guigu   自定义
     * @param configurer
     */
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        WebMvcConfigurer.super.configureContentNegotiation(configurer);

        //基于参数的内容协商策略
        Map<String, MediaType> mediaTypes = new HashMap<String, MediaType>();
        mediaTypes.put("json", MediaType.APPLICATION_JSON);
        mediaTypes.put("xml", MediaType.APPLICATION_XML);
        mediaTypes.put("x-guigu", MediaType.parseMediaType("application/x-guigu"));
        ParameterContentNegotiationStrategy parameterStrategy = new ParameterContentNegotiationStrategy(mediaTypes);

        //基于请求头的内容协商策略(此处不添加，会导致默认的请求头的内容协商策略失效)
        HeaderContentNegotiationStrategy headerContentNegotiationStrategy = new HeaderContentNegotiationStrategy();

        //将自定义的参数内容协商策略添加到系统中
        configurer.strategies(Arrays.asList(parameterStrategy, headerContentNegotiationStrategy));
    }

    /**
     * 自定义拦截器:
     *      1、编写一个拦截器实现HandlerInterceptor接口
     *      2、拦截器注册到容器中（实现WebMvcConfigurer的addInterceptors）
     *      3、指定拦截规则【如果是拦截所有，静态资源也会被拦截】
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebMvcConfigurer.super.addInterceptors(registry);
        //将自定义的拦截器添加到系统中
        registry.addInterceptor(new CustomerInterceptor())
                //所有请求都被拦截包括静态资源
                .addPathPatterns("/**")
                //放行的请求
                .excludePathPatterns("/","/login","/css/**","/fonts/**","/images/**","/js/**","/sayHello/**","/helloWord/**");
    }
}
