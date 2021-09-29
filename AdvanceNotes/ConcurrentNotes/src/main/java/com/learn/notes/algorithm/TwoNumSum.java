package com.learn.notes.algorithm;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

public class TwoNumSum {

    public static void main(String[] args) {
        int[] arr = {3,2,4};
        System.out.println(JSON.toJSONString(TwoNumSum.twoSumMap(arr, 6)));
    }

    /**
     * 方法一：遍历的方式暴力求和：
     *    时间复杂度：O(N^2)，其中N是数组中的元素数量。最坏情况下数组中任意两个数都要被匹配一次。
     *    空间复杂度：O(1)。
     */
    public int[] twoSumNormal(int[] nums, int target) {
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }

    /**
     * 方法二：哈希表
     *    时间复杂度：O(N)，其中 N 是数组中的元素数量。对于每一个元素 x，我们可以 O(1) 地寻找 target - x。
     *    空间复杂度：O(N)，其中 N 是数组中的元素数量。主要为哈希表的开销。。
     *
     */
    public static int[] twoSumMap(int[] nums, int target) {
        Map<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (hashMap.containsKey(target - nums[i])) {
                return new int[]{hashMap.get(target - nums[i]), i+1};
            } else {
                hashMap.put(nums[i], i+1);
            }
        }
        return new int[0];
    }
}
