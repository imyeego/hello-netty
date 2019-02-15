package com.imyeego.algorithm;

import java.util.BitSet;

/**
 * @authur : liuzhao
 * @time : 2019/2/15
 * @email : imyeego@gmail.com
 * @description : 1 of numbers in int n
 */
public class Offer10 {

    public static void main(String[] args) {
        int[] res = countBits(0);
        for (int i : res){
            System.out.print(i + ", ");
        }

    }

    /**
     * calculate numbers of 1 in integer n
     * from Offer#10 & LeetCode#191
     * @param n
     * @return
     */
    public static int numbersOfOne(int n){
        int count = 0;
        while (n != 0) {
            count ++;
            n &= n - 1;
        }
        return count;
    }

    /**
     * counting bits
     * from LeetCode#338
     * @param num
     * @return
     */
    public static int[] countBits(int num) {
        int[] res = new int[num + 1];
        res[0] = 0;
        for (int i = 1; i <= num; i++) {
            res[i] = numbersOfOne(i);
        }
        return res;
    }
}
