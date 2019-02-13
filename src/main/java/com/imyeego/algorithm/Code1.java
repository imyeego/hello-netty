package com.imyeego.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * @authur : liuzhao
 * @time : 2019/2/11
 * @email : imyeego@gmail.com
 * @description : two sum
 */
public class Code1 {

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int[] res = twoSum(nums, 22);
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }
    }

    /**
     *
     * @param nums
     * @param target
     * @return int []
     * time complex : O(n*n)
     * space complexity: O(1)
     */
    public static int[] twoSum(int[] nums, int target) {
        if (nums.length > 1){
            for (int i = 0; i < nums.length; i ++){
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[i] + nums[j] == target){
                        return new int[] {i, j};
                    }
                }
            }
        }
        return null;
    }

    /**
     *
     * @param nums
     * @param target
     * @return int[]
     * time complexity : O(n)
     * space complexity: O(n)
     */
    public static int[] twoSumByMap(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}
