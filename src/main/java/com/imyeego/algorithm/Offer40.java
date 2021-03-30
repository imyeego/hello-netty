package com.imyeego.algorithm;

/**
 * @authur : liuzhao
 * @date : 3/30/21:9:08 PM
 * @des : 考察异或运算符的使用
 */
public class Offer40 {

    public static void main(String[] args) {
        int[] nums = {2, 4, 3, 6, 3, 2, 5, 5};

        int[] res = findAppearOnce(nums);
        for (int num : res) {
            System.out.println(num);
        }
    }

    static int[] findAppearOnce(int[] nums) {
        if (nums == null || nums.length < 2) return nums;


        int xor = nums[0];
        for (int i = 1; i < nums.length; i ++) {
            xor ^= nums[i];
        }

        int flag = xor & -xor;

        int num1 = 0, num2 = 0;

        for (int num : nums) {
            if ((num & flag) == flag) {
                num1 ^= num;
            } else {
                num2 ^= num;
            }
        }

        return new int[]{num1, num2};
    }
}
