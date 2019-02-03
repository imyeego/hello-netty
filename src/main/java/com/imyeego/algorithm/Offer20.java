package com.imyeego.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @Authur: liuzhao
 * @Date: 2019/2/2 0002 00:02
 * @Email: imyeego@gmail.com
 * @Description: 顺时针打印数组
 */
public class Offer20 {

    private static int m, n;

    public static void main(String[] args) {
        int[] v1 = new int[]{1, 2, 3, 4};
        int[] v2 = new int[]{5, 6, 7, 8};
        int[] v3 = new int[]{9, 10, 11, 12};
        int[] v4 = new int[]{13, 14, 15, 16};
        final int[][] v = new int[][]{v1, v2, v3, v4};

//        System.out.println(v.length);

        List<Integer> result = printMatrix(v);

        for (int i : result){
            System.out.print(i + ", ");
        }

    }

    /**
     *  Time complexity O(n)
     *  Space complexity O(n)
     * @param v input two-dimen array
     * @return result one-dimen array
     */
    private static List<Integer> printMatrix(final int[][] v){
        m = v.length;
        n = v[0].length;
        int count = m * n;
        int i = 0, j = 0, d = 0;
        int[][] b = new int[m][n];

        final int[][] D = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        List<Integer> list = new ArrayList<>();
        if (m == 0 || n == 0){
            return list;
        }
        while ((count --) > 0){
            list.add(v[i][j]);
            b[i][j] = 1;
            if (!judge(i + D[d][0], j + D[d][1], b)){
                ++d;
                d %= 4;
            }
            i += D[d][0];
            j += D[d][1];
        }

        return list;
    }

    private static boolean judge(final int i, final int j, final int[][] b){
        return i >= 0 && i < n && j >= 0 && j < m && b[i][j] == 0;
    }


}
