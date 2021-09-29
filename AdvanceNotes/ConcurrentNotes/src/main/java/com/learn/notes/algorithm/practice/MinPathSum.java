package com.learn.notes.algorithm.practice;

import com.learn.notes.algorithm.SpiralOrder;

public class MinPathSum {

    public static void main(String[] args) {
        int[][] matrix = new int[4][];
        matrix[0] = new int[]{73,4,38,35,11,99,5,51,94,35,30,30,55,29,7,50,43,18,61,75,86,45,45,11,17,82,97,8,4,91,0,7,22,39,70,14,39,80,24,15,32,36,88,65,81,98,32};
        matrix[1] = new int[]{98,21,2,71,40,68,48,48,93,48,64,49,14,53,8,4,81,25,16,40,15,28,27,52,63,50,85,93,29,66,22,94,63,53,9,58,78,51,77,69,7,31,86,87,56,66,79};
        matrix[2] = new int[]{23,75,5,7,24,52,84,8,67,47,34,57,56,84,30,65,49,52,91,50,74,81,40,51,41,70,80,59,22,58,96,40,11,21,82,79,74,44,73,66,36,81,79,86,33,0,38};
        matrix[3] = new int[]{6,33,91,7,69,63,24,27,82,81,77,21,68,76,14,49,53,20,86,48,85,95,75,16,18,16,52,98,60,5,50,99,96,3,46,62,20,50,47,6,46,28,40,93,51,94,59};
        MinPathSum.minPathSum(matrix);
    }

    public static int minPathSum (int[][] matrix) {
        // write code here
        return minPathSum(matrix,0, 0);

    }

    public static int minPathSum (int[][] matrix, int x, int y) {
        // write code here
        try {
            int xSum = 0;
            int ySum = 0;
            int sum = matrix[x][y];
            if(y < matrix[0].length-1){
                ySum = minPathSum(matrix, x, y+1);
            }
            if(x < matrix.length-1){
                xSum = minPathSum(matrix, x+1, y);
            }
            sum = sum + Math.min(xSum, ySum);
            return sum;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
