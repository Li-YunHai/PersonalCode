package com.learn.notes.algorithm.practice;

import java.util.concurrent.atomic.AtomicInteger;

public class HanoiTower {

    public static void main(String[] args) {
        HanoiTower hanoiTower = new HanoiTower();
        hanoiTower.hanoiTower(5, 'A', 'B', 'C', new AtomicInteger());
    }


    public void hanoiTower(int x,  char a, char b, char c, AtomicInteger stepNum){
        if(x == 1){
            printStepInfo(x, a, b, c, stepNum);
        } else if (x > 1){
            hanoiTower(x-1, a, c, b, stepNum);
            printStepInfo(x, a, b, c, stepNum);
            hanoiTower(x-1, b, a, c, stepNum);
        }
    }

    public void printStepInfo(int x,  char a, char b, char c, AtomicInteger stepNum){
        System.out.println("第" + stepNum.incrementAndGet() + "步,移动"+ x + "号盘,：" + a + "塔->" + c + "塔");
    }
}
