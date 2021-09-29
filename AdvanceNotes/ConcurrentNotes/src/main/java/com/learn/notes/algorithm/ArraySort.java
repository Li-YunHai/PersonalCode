package com.learn.notes.algorithm;

import com.alibaba.fastjson.JSON;

/**
 * 数组排序
 */
public class ArraySort {

    public static void main(String[] args) {
        int arr[] = {7, 5, 3, 2, 4, 1, 8, 9, 6};
        arr = ArraySort.quickSort(arr, 0, arr.length - 1);
        System.out.println(JSON.toJSONString(arr));
    }


    /**
     * 冒泡排序
     *      1、通过每一次遍历获取最大/最小值
     *      2、将最大值/最小值放在尾部/头部
     *      3、然后除开最大值/最小值，剩下的数据在进行遍历获取最大/最小值
     * @param arr
     * @return
     */
    public static int[] popSort(int [] arr){
        for (int i = 0; i < arr.length; i++) {
            //外层循环，遍历次数
            for (int j = 0; j < arr.length - i - 1; j++) {
                //内层循环，升序（如果前一个值比后一个值大，则交换）
                //内层循环一次，获取一个最大值
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

    /**
     * 选择排序
     *      1、将第一个值看成最小值
     *      2、然后和后续的比较找出最小值和下标
     *      3、交换本次遍历的起始值和最小值
     *      说明：每次遍历的时候，将前面找出的最小值，看成一个有序的列表，后面的看成无序的列表，然后每次遍历无序列表找出最小值。
     * @param arr
     * @return
     */
    public static int[] selectSort(int [] arr){
        //选择
        for (int i = 0; i < arr.length; i++) {
            //默认第一个是最小的。
            int min = arr[i];
            //记录最小的下标
            int index = i;
            //通过与后面的数据进行比较得出，最小值和下标
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    index = j;
                }
            }
            //然后将最小值与本次循环的，开始值交换
            int temp = arr[i];
            arr[i] = min;
            arr[index] = temp;
            //说明：将i前面的数据看成一个排好的队列，i后面的看成一个无序队列。每次只需要找无需的最小值，做替换
        }
        return arr;
    }

    /**
     * 插入排序：
     *      1、默认从第二个数据开始比较
     *      2、如果第二个数据比第一个小，则交换。然后在用第三个数据比较，如果比前面小，则插入（交换）。否则，退出循环
     *      说明：默认将第一数据看成有序列表，后面无序的列表循环每一个数据，如果比前面的数据小则插入（交换）。否则退出。
     * @param arr
     * @return
     */
    public static int[] insertSort(int [] arr){
        //插入排序
        for (int i = 1; i < arr.length; i++) {
            // 记录要插入的数据
            int tmp = arr[i];

            //外层循环，从第二个开始比较
            // 从已经排序的序列最右边的开始比较，找到比其小的数
            int j = i;
            while (j > 0 && tmp < arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;
            }

            // 存在比其小的数，插入
            if (j != i) {
                arr[j] = tmp;
            }
        }
        return arr;
    }


    /**
     * 快速排序
     *      1、确认列表第一个数据为中间值，第一个值看成空缺（低指针空缺）。
     *      2、然后在剩下的队列中，看成有左右两个指针（高低）。
     *      3、开始高指针向左移动，如果遇到小于中间值的数据，则将这个数据赋值到低指针空缺，并且将高指针的数据看成空缺值（高指针空缺）。然后先向右移动一下低指针，并且切换低指针移动。
     *      4、当低指针移动到大于中间值的时候，赋值到高指针空缺的地方。然后先高指针向左移动，并且切换高指针移动。重复c、d操作。
     *      5、直到高指针和低指针相等时退出，并且将中间值赋值给对应指针位置。
     *      6、然后将中间值的左右两边看成行的列表，进行快速排序操作。
     *
     * 测试数据：{7, 5, 3, 2, 4, 1, 8, 9, 6}
     * @param arr
     * @param low
     * @param high
     * @return
     */
    public static int[] quickSort(int[] arr, int low, int high) {
        //如果指针在同一位置(只有一个数据时)，退出
        if (high - low < 1) {
            return null;
        }
        //标记，从高指针开始，还是低指针（默认高指针）
        boolean flag = true;
        //记录指针的其实位置
        int start = low;
        int end = high;
        //默认中间值为低指针的第一个值
        int midValue = arr[low];
        while (true) {
            //高指针移动
            if (flag) {
                //如果列表右方的数据大于中间值，则向左移动
                if (arr[high] > midValue) {
                    high--;
                } else if (arr[high] < midValue) {
                    //如果小于，则覆盖最开始的低指针值，并且移动低指针，标志位改成从低指针开始移动
                    arr[low] = arr[high];
                    low++;
                    flag = false;
                }
            } else {
                //如果低指针数据小于中间值，则低指针向右移动
                if (arr[low] < midValue) {
                    low++;
                } else if (arr[low] > midValue) {
                    //如果低指针的值大于中间值，则覆盖高指针停留时的数据，并向左移动高指针。切换为高指针移动
                    arr[high] = arr[low];
                    high--;
                    flag = true;
                }
            }
            //当两个指针的位置相同时，则找到了中间值的位置，并退出循环
            if (low == high) {
                arr[low] = midValue;
                break;
            }
        }
        //然后出现有，中间值左边的小于中间值。右边的大于中间值。
        //然后在对左右两边的列表在进行快速排序
        quickSort(arr, start, low -1);
        quickSort(arr, low + 1, end);
        return arr;
    }
}
