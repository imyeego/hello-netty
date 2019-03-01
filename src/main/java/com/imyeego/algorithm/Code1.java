package com.imyeego.algorithm;

import java.util.*;
import java.util.stream.IntStream;

/**
 * @authur : liuzhao
 * @time : 2019/2/11
 * @email : imyeego@gmail.com
 * @description : two sum
 */
public class Code1 {

    public static void main(String[] args) {
        int[] nums = {0, 0, 0, 0};
        List<List<Integer>> res = threeSum(nums);
//        for (int i = 0; i < res.length; i++) {
//            System.out.println(res[i]);
//        }
        System.out.println(res);

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

    /**
     * three sum from LeetCode#15
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length < 3) return res;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i ++){
            for (int j = i + 1; j < nums.length; j++) {
                int complement = - (nums[i] + nums[j]);
                if (map.containsKey(complement)){
                    int[] array = {nums[i], nums[j], complement};
                    Arrays.sort(array);
                    List<Integer> list = arrayToList(array);
                    if (!res.contains(list))
                        res.add(list);
                }
            }
            map.put(nums[i], i);
        }
        return res;
    }

    private static List<Integer> arrayToList(int[] array) {
        List<Integer> res = new ArrayList<>();
        for (int num : array) {
            res.add(num);
        }
        return res;
    }
}
