package com.learn.notes.algorithm;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonAlias;
import org.apache.commons.lang.StringUtils;

import java.util.*;

public class Main{
    public static void main(String[] args){

        /*
        Scanner in = new Scanner(System.in);
        while(in.hasNextLine()){
            String lineDate = in.nextLine();
            int n = lineDate.length()%8 == 0 ?  lineDate.length()/8 : lineDate.length()/8 + 1;
            for(int i=0; i<n; i++){
                int start = i * 8;
                int end = (i+1) * 8 <= lineDate.length() ? (i+1) * 8 : lineDate.length();
                System.out.println(StringUtils.rightPad(lineDate.substring(start,end), 8, "0"));
            }
        }
        in.close();
        */

        /**
         * 16进制装10进制
         * 0xAA  -->  170
         */
        /*
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String str = sc.nextLine();
            String num = str.substring(2,str.length());
            int result = 0;
            int power = 1;
            for(int i = num.length() - 1; i >= 0; i--){
                char c = num.charAt(i);
                if(c >= '0' && c <= '9'){
                    result += (c - '0') * power;
                }else if (c >= 'A' && c <= 'F'){
                    result += (c - 'A' + 10) * power;
                }
                power *= 16;
            }
            System.out.println(result);
        }
        sc.close();
        */

        Scanner scanner = new Scanner(System.in);

        long num = scanner.nextLong();

        for (long i = 2; i <= num; ++i) {
            while (num % i == 0) {
                System.out.print(i + " ");
                num /= i;
            }
        }
        System.out.println();

    }
}