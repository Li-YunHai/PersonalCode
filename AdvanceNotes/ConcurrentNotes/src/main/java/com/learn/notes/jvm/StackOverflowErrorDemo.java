package com.learn.notes.jvm;

/**
 * 栈空间溢出错误
 * 场景：
 *      循环递归调用，导致方法栈内存异常
 */
public class StackOverflowErrorDemo  {

    public static void main(String[] args) {
        main(args);
    }

}
