package com.learn.notes.spring.dependencies;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring循环依赖Circular Dependencies
 * 异常信息：Caused by: org.springframework.beans.factory.BeanCurrentlyInCreationException
 *
 * 解决方式：Spring内部通过三级缓存来解决循环依赖 - DefaultSingletonBeanRegistry
 *      第一级缓存（单例池）：singletonObjects：存放已经经历了完整生命周期的Bean对象。
 *      第二级缓存：earlySingletonObjects，存放早期暴露出来的Bean对象，Bean的生命周期未结束（属性还未填充完）。
 *      第三级缓存：Map<String, ObjectFactory<?>> singletonFactories，存放可以生成Bean的工厂。
 *
 * 四大方法：
 *      getSingleton()、doCreateBean()、populateBean()、addSingleton()
 *
 * 只有单例的bean会通过三级缓存提前暴露来解决循环依赖的问题，而非单例的bean，
 * 每次从容器中获取都是一个新的对象，都会重新创建，所以非单例的bean是没有缓存的，不会将其放到三级缓存中。
 *
 * A / B两对象在三级缓存中的迁移说明
 *      1、A创建过程中需要B，于是A将自己放到三级缓里面，去实例化B。
 *      2、B实例化的时候发现需要A，于是B先查一级缓存，没有，再查二级缓存，还是没有，再查三级缓存，找到了A然后把三级缓存里面的这个A放到二级缓存里面，并删除三级缓存里面的A。
 *      3、B顺利初始化完毕，将自己放到一级缓存里面（此时B里面的A依然是创建中状态)，然后回来接着创建A，此时B已经创建结束，直接从一级缓存里面拿到B，然后完成创建，并将A自己放到一级缓存里面。
 *
 */
public class CircularDependencies {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        A a = context.getBean("a", A.class);
        B b = context.getBean("b", B.class);
    }
}
