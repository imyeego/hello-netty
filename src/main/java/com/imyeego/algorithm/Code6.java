package com.imyeego.algorithm;

/**
 * @authur : liuzhao
 * @date : 4/10/21:5:43 PM
 * @des :
 */
public class Code6 {

    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
        System.out.println(convert(s, 3));

    }

    /**
     * 1,1,1,2,3,3,3,4,5,5,5,6,7,7
     * @param s
     * @param numRows
     * @return
     */
    static String convert(String s, int numRows) {
        if (numRows == 1) return s;
        int length = s.length();
        int col = 1;
        int temp = numRows - 1;
        boolean isAdd = false;
        for (int i = 1; i < length; i++) {
            col += isAdd ? 1 : 0;
            if (i % temp == 0) isAdd = !isAdd;
        }
        System.out.println("col: " + col);
        char[][] matrix = new char[numRows][col];
        int i = 0, j = 0, p = 0;
        int[][] t = {{1, 0}, {-1, 1}};
        matrix[0][0] = s.charAt(0);

        for (int k = 1; k < length; k ++) {
            i += t[p][0];
            j += t[p][1];
            matrix[i][j] = s.charAt(k);
            if (k % temp == 0) {
                p++;
                p %= 2;
            }

        }
        char[] res = new char[s.length()];
        i = 0;
        for (int k = 0; k < matrix.length; k++) {
            for (int m = 0; m < col; m++) {
                if (matrix[k][m] != '\u0000') res[i++] = matrix[k][m];
            }
        }

        return new String(res);


    }
}
