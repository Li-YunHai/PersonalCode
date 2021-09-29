package com.learn.notes.algorithm;

import com.alibaba.fastjson.JSON;

public class SingleNumbers {

    public static void main(String[] args) {
        int [] nums = new int[]{1,2,3,3,};
        System.out.println(JSON.toJSONString(SingleNumbers.singleNumbers(nums)));
    }

    public static int[] singleNumbers(int[] nums) {
        int ret = 0;
        for (int n : nums) {
            ret ^= n;
        }
        int div = 1;
        while ((div & ret) == 0) {
            div <<= 1;
        }
        int a = 0, b = 0;
        for (int n : nums) {
            if ((div & n) != 0) {
                a ^= n;
            } else {
                b ^= n;
            }
        }
        return new int[]{a, b};
    }

}
