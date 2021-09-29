package com.learn.notes.algorithm;

public class SearchNum {

    public static void main(String[] args) {
        int[] arr = {1,2,4,4,5};
        System.out.println(SearchNum.search(arr, 4));
    }

    public static int search (int[] nums, int target) {
        int x = handler(nums, 0, nums.length-1,target);
        // write code here
        if(x == -1){
            return -1;
        } else {
            while(x>0 && nums[x-1] == target) {
                x = x - 1;
            }
            return x;
        }
    }

    public static int handler(int[] nums, int start, int end, int target){
        if(nums.length == 0){
            return -1;
        } else if(start == end){
            return nums[start] == target ? start : -1;
        } else if(start+1 == end){
            if(nums[start] == target){
                return start;
            } else if(nums[end] == target){
                return end;
            } else {
                return -1;
            }
        } else {
            int middle = (start + end)/2;
            if(nums[middle] == target){
                return middle;
            } else if(nums[middle] > target){
                return handler(nums, start, middle, target);
            } else {
                return handler(nums, middle, end, target);
            }
        }
    }
}
