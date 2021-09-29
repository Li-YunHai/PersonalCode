package com.springboot.notes.config;

import com.springboot.notes.model.Car;
import com.springboot.notes.model.Dog;
import com.springboot.notes.model.Person;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.*;

/**
 * 1、配置类本身会注册到spring容器中
 * 2、配置类中的bean都是单例的，包括其本身
 * 3、proxyBeanMethods
 *      true：full重量级模式，先从容器中获取，没有则创建对象，并加入到容器中
 *      false：lite轻量级模式，总是创建新的对象
 */
//@Profile("test") //在这里指定当前配置类的生效环境
@Data
@Configuration
@AutoConfigureBefore(name = {"person"})
public class SpringBootConfig {

    @Value("${JAVA_HOME}")
    public String JAVA_HOME;

    /**
     * Spring容器管理对象的生命周期：
     *      1、singleton单例模式模式在容器销毁后会自动调用destroyMethod销毁，prototype原型模式不会自动销毁
     *      2、InitializingBean初始化接口、DisposableBean销毁对象接口
     *      3、使用JSR250注解：@PostConstruct对象装配完成后执行、@PreDestroy对象销毁前执行
     *      4、BeanPostProcessor后置处理器接口
     *              postProcessBeforeInitialization在容器中所有组件init初始化之前执行
     *              postProcessAfterInitialization在容器中所有组件init初始化之后执行
     * @return
     */
    @Bean(name = "dog", initMethod = "initMethod", destroyMethod = "destroyMethod")
    @Scope("singleton") //singleton、prototype、request、session
    @Lazy //懒加载，需与singleton配合使用，默认IOC容器初始化时不加载，第一次获取时才加载单例
    public Dog dog(){
        return new Dog();
    }

    @Bean(name = "car")
    @DependsOn(value = {"person", "phone"})
    @ConditionalOnClass(value = {Person.class}) //condition条件装配
    @ConditionalOnBean(name =  {"dog"}) //condition条件装配
    //@ConditionalOnMissingBean(name =  {"person"}) //condition条件装配
    public Car car(){
        return new Car();
    }

}
