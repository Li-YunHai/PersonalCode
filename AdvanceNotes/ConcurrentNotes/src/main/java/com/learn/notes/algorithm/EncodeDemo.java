package com.learn.notes.algorithm;


import com.alibaba.fastjson.JSON;

public class EncodeDemo {

    /**
     * 加密值数组
     */
    static final int[] encodeArray = {5,1,2,3,4};
    /**
     * 解密值数组
     */
    static final int[] decodeArray = {2,3,4,5,1};

    /**
     * 整型数组加密
     * 输入：{1,2,3,4,5}
     * 输出：{5,1,2,3,4}
     * @param input
     * @return
     */
    public static int[] encode(int[] input){
        int[] result = new int[input.length];
        for(int i=0; i<input.length; i++){
            //加密值对应的index
            int valueIndex = input[i] - 1;
            //待加密数值的取值范围：1~5, 对应的数组坐标：0~4, 补充处理区间外的值
            if(valueIndex < 0 || valueIndex > encodeArray.length -1){
                //待加密数值小于1或大于5时的处理：此处按默认值0处理。
                result[i] = 0;
            } else {
                result[i] = encodeArray[valueIndex];
            }
        }
        return result;
    }

    /**
     * 整型数组解密
     * 输入：{5,1,2,3,4}
     * 输出：{1,2,3,4,5}
     * @param input
     * @return
     */
    public static int[] decode(int[] input){
        int[] result = new int[input.length];
        for(int i=0; i<input.length; i++){
            //加密值对应的index
            int valueIndex = input[i] - 1;
            //待解密数值的取值范围：1~5, 对应的数组坐标：0~4, 补充处理区间外的值
            if(valueIndex < 0 || valueIndex > encodeArray.length -1){
                //待解密数值小于1或大于5时的处理：此处按默认值0处理。
                result[i] = 0;
            } else {
                result[i] = decodeArray[valueIndex];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] input = {1,2,3,4,5};
        int[] encodeValue = EncodeDemo.encode(input);
        System.out.println("加密后的值：" + JSON.toJSONString(encodeValue));

        int[] decodeValue = EncodeDemo.decode(encodeValue);
        System.out.println("解密后的值：" + JSON.toJSONString(decodeValue));

        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
