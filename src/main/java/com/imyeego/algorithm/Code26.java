package com.imyeego.algorithm;

import java.util.Base64;

/**
 * @authur : liuzhao
 * @time : 2019/2/27
 * @email : imyeego@gmail.com
 * @description : remove duplicates from sorted array
 */
public class Code26 {

    public static void main(String[] args) {
        int[] nums = {1, 1, 2, 4,4,6,6,6,6, 9, 10, 10, 10};
        int length = removeDuplicates(nums);

        for (int i = 0; i < length; i++) {
            System.out.println(nums[i]);
        }
    }

    public static int removeDuplicates(int[] nums) {
        if (nums.length == 1) return 1;
        int a = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                nums[++a] = nums[i];
            }
        }
        return a + 1;
    }
}
