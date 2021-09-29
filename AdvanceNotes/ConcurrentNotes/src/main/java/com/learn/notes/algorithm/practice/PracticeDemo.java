package com.learn.notes.algorithm.practice;

import com.alibaba.fastjson.JSON;

import java.util.*;

public class PracticeDemo {

    public static void main(String[] args) {
        /*
        int arr[] = {8, 5, 9, 7, 4, 6, 3, 1, 2};
        arr = insertSort(arr);
        arr = quickSort(arr, 0 , arr.length-1);
        System.out.println(JSON.toJSONString(arr));
        */
        System.out.println(JSON.toJSONString(PracticeDemo.isValid("[]")));
    }

    /**
     * 冒泡排序
     * @param arr
     * @return
     */
    public static int[] popSort(int arr[]) {
        System.out.println(JSON.toJSONString(arr));
        int tem = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i -1; j++) {
                if (arr[j] > arr[j+1]) {
                    tem = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tem;
                }
            }
            System.out.println(JSON.toJSONString(arr));
        }
        return arr;
    }

    /**
     * 选择排序
     * @param arr
     * @return
     */
    public static int[] selectSort(int arr[]) {
        System.out.println(JSON.toJSONString(arr));
        int tem = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    tem = arr[j];
                    arr[j] = arr[i];
                    arr[i] = tem;
                }
            }
            System.out.println(JSON.toJSONString(arr));
        }
        return arr;
    }

    /**
     * 插入排序
     * @param arr
     * @return
     */
    public static int[] insertSort(int arr[]) {
        System.out.println(JSON.toJSONString(arr));
        int tem = Integer.MIN_VALUE;
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j-1]) {
                    tem = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = tem;
                }
            }
            System.out.println(JSON.toJSONString(arr));
        }
        return arr;
    }

    /**
     * 快速排序
     * @param arr
     * @return
     */
    public static int[] quickSort(int arr[], int start, int end) {
        System.out.println(JSON.toJSONString(arr));
        if(end - start < 1){
            return null;
        }
        int midValue = arr[start];
        int low = start;
        int high = end;
        boolean flag = true;
        while(true){
            if(flag){
                if(arr[high] >= midValue){
                    high--;
                } else if (arr[high] < midValue){
                    arr[low] = arr[high];
                    flag = false;
                    low++ ;
                }
            } else {
                if(arr[low] <= midValue){
                    low++ ;
                } else if (arr[low] > midValue){
                    arr[high] = arr[low];
                    flag = true;
                    high--;
                }
            }
            if(low == high){
                arr[low] = midValue;
                break;
            }
            System.out.println(JSON.toJSONString(arr));
        }
        quickSort(arr, start, low-1);
        quickSort(arr, low+1, end);
        return arr;
    }

    public static boolean isValid (String s) {
        // write code here
        Map<Character,Character> flagMap = new HashMap();
        flagMap.put(')','(');
        flagMap.put('}','{');
        flagMap.put(']','[');
        Stack<Character> stack = new Stack();
        for(int i=0; i<s.length(); i++){
            if(flagMap.containsKey(s.charAt(i))){
                if(stack.isEmpty()){
                    return false;
                } else if(flagMap.get(s.charAt(i)) != stack.pop()){
                    return false;
                } else {
                    continue;
                }
            } else {
                stack.add(s.charAt(i));
            }
        }
        if(stack.size() > 0){
            return false;
        }
        return true;
    }
}
