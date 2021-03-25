package com.imyeego.algorithm;

import java.util.Arrays;
import java.util.Collections;

/**
 * @authur : liuzhao
 * @date : 3/4/21:3:13 PM
 * @des :
 */
public class Code300 {

    public static void main(String[] args) {
        int[] array = {0,1,0,3,2,3};
        System.out.println(lengthOfLIS(array));

    }

    public static int lengthOfLIS(int[] nums) {
        int len = nums.length;
        int[] l = new int[len];
        l[0] = 1;
        for (int i = 1; i < len; i ++) {
            int leng = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    leng = Math.max(leng,l[j] + 1);
                }
            }

            l[i] = leng;
        }

        int max = l[0];
        for (int i = 1; i < len; i++) {
            if (l[i] >= max) max = l[i];
        }
        return max;
    }
}
