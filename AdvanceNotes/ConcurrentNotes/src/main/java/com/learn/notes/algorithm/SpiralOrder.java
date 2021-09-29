package com.learn.notes.algorithm;

import java.util.ArrayList;

public class SpiralOrder {



    public static void main(String[] args) {
        int[][] matrix = new int[3][];
        matrix[0] = new int[]{1,2,3};
        matrix[1] = new int[]{4,5,6};
        matrix[2] = new int[]{7,8,9};
        SpiralOrder.spiralOrder(matrix);
    }

    public static ArrayList<Integer>  spiralOrder(int[][] matrix) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        handler(result, matrix, 0, 0, 0, matrix.length, 0, 0, matrix[0].length);
        return result;
    }

    public static void handler(ArrayList<Integer> result, int[][] matrix, int num, int x, int xStart, int xEnd, int y, int yStart, int yEnd){
        int index = num/4;
        if(result.size() == matrix.length * matrix[0].length){
            return;
        }
        if(num%4 == 0){
            while(y < yEnd){
                result.add(matrix[x][y]);
                y ++;
            }
            y = y - 1;
            x = x + 1;
            xStart++;
            handler(result, matrix, num+1, x, xStart, xEnd, y, yStart, yEnd);
        }
        else if(num%4 == 1){
            while(x < xEnd){
                result.add(matrix[x][y]);
                x ++;
            }
            x = x - 1;
            y = y - 1;
            yEnd--;
            handler(result, matrix, num+1, x, xStart, xEnd, y, yStart, yEnd);
        }
        else if(num%4 == 2){
            while(y >= yStart){
                result.add(matrix[x][y]);
                y --;
            }
            y = y + 1;
            x = x - 1;
            xEnd--;
            handler(result, matrix, num+1, x, xStart, xEnd, y, yStart, yEnd);
        }
        else {
            while(x >= xStart){
                result.add(matrix[x][y]);
                x --;
            }
            x = x + 1;
            y = y + 1;
            yStart++;
            System.out.println(x);
            handler(result, matrix, num+1, x, xStart, xEnd, y, yStart, yEnd);
        }
        //handler(result, matrix, num+1, x, xStart, xEnd, y, yStart, yEnd);
    }
}
