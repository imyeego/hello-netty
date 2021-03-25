package com.imyeego.algorithm;

import java.util.Arrays;
import java.util.Collections;

/**
 * @authur : liuzhao
 * @time : 2019/2/26
 * @email : imyeego@gmail.com
 * @description :
 */
public class Code48 {

    public static void main(String[] args) {
//        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
//        antiRotate(matrix);
//        for (int i = 0; i < matrix.length; i++) {
//            for (int j = 0; j < matrix[0].length; j ++) {
//                System.out.print(matrix[i][j] + " ");
//            }
//        }

        int[] a = {1, 2};
        a[0] ^= a[1];
        a[1] ^= a[0];
        a[0] ^= a[1];
        System.out.println(a[0]);
        System.out.println(a[1]);
    }

    /*
     * clockwise rotate
     * first reverse up to down, then swap the symmetry
     * 1 2 3     7 8 9     7 4 1
     * 4 5 6  => 4 5 6  => 8 5 2
     * 7 8 9     1 2 3     9 6 3
     */
    public static void rotate(int[][] matrix) {
        int cols = matrix[0].length;
        int rows = matrix.length;
        for (int i = 0; i < rows >> 1; i++) {
            int temp[] = matrix[i];
            matrix[i] = matrix[rows - 1 - i];
            matrix[rows - 1 - i] = temp;
        }
        for (int i = 1; i < cols; i++) {
            for (int j = 0; j < i; j ++) {
                matrix[i][j] ^= matrix[j][i];
                matrix[j][i] ^= matrix[i][j];
                matrix[i][j] ^= matrix[j][i];
            }
        }
    }

    /*
     * just swap the step implemented in clockwise rotate algorithm
     */
    public static void antiRotate(int[][] matrix) {
        int cols = matrix[0].length;
        int rows = matrix.length;
        for (int i = 1; i < cols; i++) {
            for (int j = 0; j < i; j ++) {
                matrix[i][j] ^= matrix[j][i];
                matrix[j][i] ^= matrix[i][j];
                matrix[i][j] ^= matrix[j][i];
            }
        }
        for (int i = 0; i < rows >> 1; i++) {
            int temp[] = matrix[i];
            matrix[i] = matrix[rows - 1 - i];
            matrix[rows - 1 - i] = temp;
        }
    }


}
