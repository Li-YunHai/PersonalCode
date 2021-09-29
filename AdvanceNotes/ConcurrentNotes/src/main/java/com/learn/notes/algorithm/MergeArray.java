package com.learn.notes.algorithm;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class MergeArray {

    public static void main(String[] args) {
        int[] A = {4, 5, 6, 0, 0, 0};
        int[] B = {1, 2, 3};
        MergeArray.merge(A, 3, B, 3);
    }

    public static void merge(int A[], int m, int B[], int n) {
        int[] arr = A.clone();
        int x = 0;
        int y = 0;
        for (int i = 0; i < m + n; i++) {
            if (y == n && x < m) {
                A[i] = arr[x];
                x = x + 1;
            } else if (x == m && y < n) {
                A[i] = B[y];
                y = y + 1;
            } else if (arr[x] > B[y]) {
                A[i] = B[y];
                y = y + 1;
            } else {
                A[i] = arr[x];
                x = x + 1;
            }
        }
        System.out.println(JSON.toJSONString(A));
    }


}
