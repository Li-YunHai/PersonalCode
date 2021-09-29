package com.learn.notes.algorithm;

import com.learn.notes.spring.dependencies.A;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的中位数
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 *
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 *
 */
public class FindMedianSortedArrays {

    public static void main(String[] args) {
        int[] nums1 = {1,3,5,6,7,8,9,10,11,12};
        int[] nums2 = {1,2,4,9};
        double median = FindMedianSortedArrays.findMedianSortedArraysNormal(nums1, nums2);
        System.out.println(median);

        median = FindMedianSortedArrays.findMedianSortedArrays2(nums1, nums2);
        System.out.println(median);

    }

    public static double findMedianSortedArraysNormal(int[] nums1, int[] nums2) {
        List<Integer> newList = new ArrayList<>(nums1.length + nums2.length);
        List<Integer> newList1 = Arrays.stream( nums1 ).boxed().collect(Collectors.toList());
        List<Integer> newList2 = Arrays.stream( nums2 ).boxed().collect(Collectors.toList());

        newList.addAll(newList1);
        newList.addAll(newList2);
        Collections.sort(newList);
        if (newList.size() % 2 == 0) {
            return (newList.get(newList.size() / 2 - 1) + newList.get(newList.size() / 2)) / 2.0;
        } else {
            return newList.get(newList.size() / 2);
        }
    }

    /**
     * 时间复杂度：遍历 len/2+1 次，len=m+n，所以时间复杂度依旧是 O(m+n)。
     * 空间复杂度：我们申请了常数个变量，也就是 m，n，len，left，right，aStart，bStart 以及 i。
     * 总共 8 个变量，所以空间复杂度是 O(1）。
     */
    public static double findMedianSortedArrays1(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        int len = m + n;
        int left = -1, right = -1;
        int aStart = 0, bStart = 0;
        for (int i = 0; i <= len / 2; i++) {
            left = right;
            if (aStart < m && (bStart >= n || A[aStart] < B[bStart])) {
                right = A[aStart++];
            } else {
                right = B[bStart++];
            }
        }
        if ((len & 1) == 0){
            return (left + right) / 2.0;
        } else {
            return right;
        }
    }


    public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int left = (n + m + 1) / 2;
        int right = (n + m + 2) / 2;
        //将偶数和奇数的情况合并，如果是奇数，会求两次同样的 k 。
        double x1 = getKth(nums1, 0, n - 1, nums2, 0, m - 1, left);
        double x2 = getKth(nums1, 0, n - 1, nums2, 0, m - 1, right);
        return (x1 + x2) * 0.5;
    }

    private static int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;
        //让 len1 的长度小于 len2，这样就能保证如果有数组空了，一定是 len1
        if (len1 > len2) return getKth(nums2, start2, end2, nums1, start1, end1, k);
        if (len1 == 0) return nums2[start2 + k - 1];

        if (k == 1) return Math.min(nums1[start1], nums2[start2]);

        int i = start1 + Math.min(len1, k / 2) - 1;
        int j = start2 + Math.min(len2, k / 2) - 1;

        if (nums1[i] > nums2[j]) {
            return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
        }
        else {
            return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
        }
    }

}
