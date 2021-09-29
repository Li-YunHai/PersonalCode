package com.springboot.notes;

import ch.qos.logback.core.db.DBHelper;
import com.alibaba.fastjson.JSON;
import com.springboot.notes.config.SpringBootConfig;
import com.springboot.notes.filter.CustomerTypeFilter;
import com.springboot.notes.model.Car;
import com.springboot.notes.model.Phone;
import com.springboot.notes.servlet.CustomerFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigurationExcludeFilter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;

import java.util.Map;

/**
 * 1、父项目做依赖管理
 *      spring-boot-dependencies几乎声明了所有开发中常用的依赖的版本号,自动版本仲裁机制
 * 2、自动配置
 *      自动配好SpringMVC
 *      自动配好Web常见功能，SpringBoot帮我们配置好了所有web开发的常见场景，如：字符编码问题
 */
@Import({DBHelper.class})  //向容器中引入指定的类，默认SpringBean的名字为全类名（带包路径）
@ImportResource(locations= {"classpath:beans.xml"})  //将配置文件中的bean加入到容器中
@ComponentScan(value = {"com.springboot"} /*,
        excludeFilters = {@ComponentScan.Filter(type = FilterType.CUSTOM,classes = {TypeExcludeFilter.class}),
                @ComponentScan.Filter(type = FilterType.CUSTOM,classes = {AutoConfigurationExcludeFilter.class}),
                @ComponentScan.Filter(type = FilterType.CUSTOM,classes = {CustomerTypeFilter.class})
        }
        */
)
/*
@ComponentScans(value={
        @ComponentScan(value = {"com.springboot"},
        excludeFilters = {@Filter(type = FilterType.CUSTOM, classes = {TypeExcludeFilter.class})
})
    excludeFilters = Filter[],指定扫描的时候排除哪些组件
    includeFilters = Filter[],指定扫描的时候需要包含哪些组件
    FilterType.ANNOTATION   按照注解
    FilterType.ASSIGNABLE_TYPE  按照指定的类型
    FilterType.ASPECTJ  使用Aspectj表达式，不常用
    FilterType.REGEX    使用正则表达式
    FilterType.CUSTOM   使用自定义规则
*/
@SpringBootConfiguration
@EnableAutoConfiguration
/*  @EnableAutoConfiguration底层源码
    一、@AutoConfigurationPackage 下的 Registrar 类将当前类扫描包路径下的所有组件批量注册到容器中
    二、@Import 下的 AutoConfigurationImportSelector 类
        1、利用getAutoConfigurationEntry(annotationMetadata);给容器中批量导入一些组件
        2、调用List<String> configurations = getCandidateConfigurations(annotationMetadata, attributes)获取到所有需要导入到容器中的配置类
        3、利用工厂加载 Map<String, List<String>> loadSpringFactories(@Nullable ClassLoader classLoader)；得到所有的组件
        4、从META-INF/spring.factories位置来加载一个文件。
        默认扫描我们当前系统里面所有META-INF/spring.factories位置的文件
        spring-boot-autoconfigure-2.3.4.RELEASE.jar包里面也有META-INF/spring.factories

@SpringBootApplication
*/
@ServletComponentScan
@EnableAspectJAutoProxy
public class SpringBootNotesApplication {

    public static void main(String[] args) {
        //传统方式配置自定义配置文件
        //ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        //通过注解配置类的方式引入配置
        //ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringBootConfig.class);

        //1、获取IOC容器
        ConfigurableApplicationContext context = SpringApplication.run(SpringBootNotesApplication.class, args);

        //2、打印IOC容器中所有组件的名字
        String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            //System.out.println(name);
        }
        System.out.println("------------------------------------------------");


        //3、从容器中获取组件
        Phone phone01 = context.getBean("phone", Phone.class);
        Phone phone02 = context.getBean("phone", Phone.class);
        System.out.println("Spring容器中的beans是否为单例" + (phone01 == phone02));

        //4、加载顺序
        System.out.println("Spring容器中的beans的加载顺序：" + context.containsBean("person"));

        //5、@Configuration默认proxyBeanMethods代理对象方法，
        //   SpringBoot总会检查容器中是否已存在配置的bean，如果有则使用现有的，没有则创建并加入到容器中，保证单实例。
        SpringBootConfig springBootConfig = context.getBean(SpringBootConfig.class);
        Car car1 = springBootConfig.car();
        Car car2 = springBootConfig.car();
        //proxyBeanMethods默认为：true 打印结果为：true，否则为false
        System.out.println(car1 == car2);

        //6、配置绑定
        Car car = context.getBean(Car.class);
        System.out.println(JSON.toJSONString(car));

        //打印系统环境变量
        System.out.println(springBootConfig.getJAVA_HOME());
        /*
        ConfigurableEnvironment environment = context.getEnvironment();
        Map<String, Object> systemProperties = environment.getSystemProperties();
        Map<String, Object> systemEnvironment = environment.getSystemEnvironment();
        System.out.println(JSON.toJSONString(systemProperties));
        System.out.println(JSON.toJSONString(systemEnvironment));
        */
    }

}
