package com.imyeego.algorithm;

/**
 * @authur : liuzhao
 * @date : 3/12/21:4:41 PM
 * @des :
 */
public class Code33 {

    public static void main(String[] args) {
        int nums[] = {4,5,6,7,0,1,2};
        System.out.println(search(nums, 3));
    }

    public static int search(int[] nums, int target) {
        if (nums.length == 1) {
            if (nums[0] == target) return 0;
            else return -1;
        }
        int length = nums.length;
        int a = 0;
        int i;
        for (i = 0; i < length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                a = i + 1;
                break;
            }
        }

        int left = a - length;
        int right = i;

        while (left <= right) {
            int mid = (left + right) >> 1;
            int index = mid >= 0 ? mid : mid + length;
            if (nums[index] == target) return index;
            else if (nums[index] > target) right = mid - 1;
            else left = mid + 1;
        }

        return -1;
    }
}
