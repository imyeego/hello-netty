package com.imyeego.algorithm;

/**
 * @Authur: liuzhao
 * @Date: 2019/2/7 0007 18:55
 * @Email: imyeego@gmail.com
 * @Description: search in 2D matrix, also be saw on LeetCode #74
 */
public class Offer3 {

    public static void main(String[] args) {
        int[][] matrix = {{1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 42}};

        System.out.println(searchMatrix(matrix, 13));
    }
    public static boolean searchMatrix(int[][] matrix, int target) {
        boolean res = false;
        int row = matrix.length;
        if (row == 0) return res;
        int col = matrix[0].length;

        // from right-top corner or left-bottom on
        for (int i = 0, j = col - 1; (i >= 0 && i < row) && (j >= 0 && j < col);){
            if (matrix[i][j] == target){
                res = true;
                break;
            } else if (matrix[i][j] > target) {
                j --;
            } else {
                i ++;
            }
        }

        return res;
    }
}
