package com.imyeego.algorithm;

/**
 * @authur : liuzhao
 * @date : 3/27/21:11:02 PM
 * @des :
 */
public class Offer31 {

    public static void main(String[] args) {
        int[] nums = {6,-3,-2,7,-15,1,2,2};

        System.out.println(maxSumSubSequence(nums));
    }

    static int maxSumSubSequence(int[] nums) {
        if (nums.length == 1) return nums[0];
        int max = Integer.MIN_VALUE;
        int sum = nums[0];

        for (int i = 1; i < nums.length - 1; i++) {
            if (sum < 0) sum = nums[i];
            else sum += nums[i];

            if (sum > max) max = sum;
        }



        return max;
    }
}
