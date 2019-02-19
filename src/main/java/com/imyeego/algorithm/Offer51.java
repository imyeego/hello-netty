package com.imyeego.algorithm;

/**
 * @authur : liuzhao
 * @time : 2019/2/19
 * @email : imyeego@gmail.com
 * @description : duplicate number in array
 */
public class Offer51 {

    public static void main(String[] args) {
        int[] array = {3,1,3,4,2};
        System.out.println(findDuplicate(array));
    }

    /**
     * from LeetCode#287 & Offer#51
     * @param nums
     * @return
     */
    public static int findDuplicate(int[] nums) {
        for (int j = 0; j < nums.length; j++) {
            if (nums[Math.abs(nums[j])] > 0 ) {
                nums[Math.abs(nums[j])] = -nums[Math.abs(nums[j])];
            } else
                return Math.abs(nums[j]);
        }
        return -1;
    }


}
