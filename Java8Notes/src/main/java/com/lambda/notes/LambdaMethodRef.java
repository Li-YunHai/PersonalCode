package com.lambda.notes;

import com.alibaba.fastjson.JSON;
import com.lambda.model.User;
import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 方法引用： 若lambda体中的内容有方法已经实现了，我们可以使用"方法引用"，方法引用是lambda表达式的另一种表现形式
 *    注意： lambda体中的参数列表、返回值类型需要与当前调用函数式接口中的方法保持一致
 *
 * 主要三种表现形式：
 *      对象::实例方法名
 *      类::静态方法名
 *      类::实例方法名
 */
public class LambdaMethodRef {

    //类::实例方法名
    @Test
    public void test1(){
        //常规的lambda表达式
        Consumer<String> consumer1 = (x)-> System.out.println(x);

        PrintStream ps = System.out;
        Consumer<String> consumer2 = (x)-> ps.println(x);

        //类实例方法引用的lambda表达式
        Consumer<String> consumer3 = System.out :: println;
        consumer3.accept("方法引用测试案例");

    }
    //对象::实例方法名
    @Test
    public void test2(){
        User user = new User();
        Supplier<String> supplier1 = () -> user.getUserName();
        System.out.println(supplier1.get());

        Supplier<Integer> supplier2 = user :: getUserAge;
        System.out.println(supplier1.get());
    }

    //类::静态方法名
    @Test
    public void test3(){
        User user1 = new User("李云海", 29, "男");
        User user2 = new User("唐文秀", 18, "女");

        BiFunction<User, User, User> function = (x ,y) -> {
            //静态方法
            Comparator<Integer> comparator = Integer::compare;
            if(comparator.compare(x.getUserAge(), y.getUserAge()) >= 0) {
                return x;
            } else {
                return y;
            }
        };
        System.out.println(JSON.toJSONString(function.apply(user1, user2)));
    }

}
