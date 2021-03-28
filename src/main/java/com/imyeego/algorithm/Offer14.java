package com.imyeego.algorithm;

/**
 * @authur : liuzhao
 * @date : 3/27/21:10:48 PM
 * @des :
 */
public class Offer14 {

    public static void main(String[] args) {
        int[] nums = {1,2,1,5,8,0,6,7,9};

        execute(nums);
        for (int num : nums) {
            System.out.print(" " + num);
        }
    }

    static void execute(int[] nums) {
        int length = nums.length;

        int left = 0, right = length - 1;
        while (left < right) {
            while ((nums[left] & 1) == 1) left ++;
            while ((nums[right] & 1) == 0) right--;
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
        }


    }
}
