package com.imyeego.algorithm;

/**
 * @authur : liuzhao
 * @date : 3/12/21:3:30 PM
 * @des :
 */
public class Code27 {

    public static void main(String[] args) {
        int nums[] = {3,2,2,3};
        int len = removeElement(nums, 2);
        for (int i = 0; i < len; i++) {
            System.out.print("" + nums[i] + ", ");
        }
    }

    public static int removeElement(int[] nums, int val) {
        if (nums.length == 0) return 0;
        int length = nums.length;
        int a = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                a++;
            }
        }

        for (int i = 0; i < a;) {
            if (nums[i] == val) {
                for (int j = i; j < length - 1; j ++) {
                    nums[j] = nums[j + 1];
                }
                continue;
            }
            i ++;
        }


        return a;
    }
}
