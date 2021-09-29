package com.learn.notes;

import com.alibaba.fastjson.JSON;
import com.learn.notes.spring.dependencies.A;
import com.learn.notes.spring.dependencies.CircularDependencies;
import com.learn.notes.springboot.Car;
import com.learn.notes.springboot.SpringBootConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * 1、父项目做依赖管理
 *      spring-boot-dependencies几乎声明了所有开发中常用的依赖的版本号,自动版本仲裁机制
 * 2、自动配置
 *      自动配好SpringMVC
 *      自动配好Web常见功能，SpringBoot帮我们配置好了所有web开发的常见场景，如：字符编码问题
 */
@Import({CircularDependencies.class})  //向容器中引入指定的类，默认SpringBean的名字为全类名（带包路径）
@ImportResource(locations= {"classpath:beans.xml"})  //将配置文件中的bean加入到容器中
@ComponentScan(value = {"com.learn.notes"})
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


    总结：
        SpringBoot先加载所有的自动配置类  xxxxxAutoConfiguration
        每个自动配置类按照条件进行生效，默认都会绑定配置文件指定的值。xxxxProperties里面拿。xxxProperties和配置文件进行了绑定
        生效的配置类就会给容器中装配很多组件
        只要容器中有这些组件，相当于这些功能就有了
        定制化配置
        用户直接自己@Bean替换底层的组件
        用户去看这个组件是获取的配置文件什么值就去修改。
        引入过程：xxxxxAutoConfiguration ---> 组件  ---> xxxxProperties里面拿值  ----> application.properties
*/
//@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {

        //1、获取IOC容器
        ConfigurableApplicationContext context = SpringApplication.run(MainApplication.class, args);

        //2、打印IOC容器中所有组件的名字
        String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }

        //3、从容器中获取组件
        A a1 = context.getBean("a", A.class);
        A a2 = context.getBean("a", A.class);
        System.out.println(a1 == a2);


        //4、配置绑定
        System.out.println(JSON.toJSONString(context.containsBean("a")));
        System.out.println(JSON.toJSONString(context.containsBean("b")));

        //5、@Configuration默认proxyBeanMethods代理对象方法，
        //   SpringBoot总会检查容器中是否已存在配置的bean，如果有则使用现有的，没有则创建并加入到容器中，保证单实例。
        SpringBootConfig springBootConfig = context.getBean(SpringBootConfig.class);
        Car car1 = springBootConfig.getCar();
        Car car2 = springBootConfig.getCar();
        //proxyBeanMethods默认为：true 打印结果为：true，否则为false
        System.out.println(car1 == car2);

        //6、配置绑定
        Car car = context.getBean(Car.class);
        System.out.println(JSON.toJSONString(car));

        CircularDependencies circularDependencies = context.getBean(CircularDependencies.class);
        System.out.println(JSON.toJSONString(circularDependencies));
    }
}

