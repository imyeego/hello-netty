package com.imyeego.algorithm;

/**
 * @authur : liuzhao
 * @date : 3/11/21:12:36 PM
 * @des :
 */
public class Code80 {

    public static void main(String[] args) {
        int[] nums = {1,1,1,2,2,2,2,2,2,2,2,3,3,3,4,5,5};

        int length = removeDuplicates(nums);
        for (int i = 0; i < length; i++) {
            System.out.print("" + nums[i] + ", ");
        }
    }

    /**
     * 时间复杂度慢于72%，有待优化
     * @param nums
     * @return
     */
    public static int removeDuplicates(int[] nums) {
        if (nums.length == 1) return 1;

        int a = 0, dupNums = 1;
        for (int i = 1; i < nums.length; i ++) {
            if (i > 1 && nums[i] > nums[a]) {
                for (int j = a + 1; j < i; j++) {
                    nums[j] = nums[i];
                }

                a++;
                dupNums = 1;
                continue;
            }

            if (nums[i] == nums[i - 1]) dupNums ++;
            if (dupNums <= 2) a++;

        }


        return a + 1;
    }

    public static int removeDuplicates1(int[] nums) {
        if (nums.length == 1) return 1;

        int a = 0;
        for (int i = 1; i < nums.length; i ++) {
            if (i - a == 2 && nums[i] > nums[a]) {
                nums[++a] = nums[i];
            }

            if (i - a > 2) {
//                if (nums[i] == nums[a]) nums[++a] = nums[i];
                if (nums[i] > nums[a]) {
                    a += 2;
                    nums[a] = nums[i];
                }
            }


        }


        return a + 1;
    }
}
