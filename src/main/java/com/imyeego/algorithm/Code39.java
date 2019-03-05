package com.imyeego.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @authur : liuzhao
 * @time : 2019/3/5
 * @email : imyeego@gmail.com
 * @description : Combination Sum with backtracking
 */
public class Code39 {

    public static void main(String[] args) {
        int[] nums = {2,5,2,1,2};
        List<List<Integer>> list = combinationSum(nums, 5);
        System.out.println(list);
    }


    /**
     * LeetCode#39 & LeetCode#40
     * @param candidates
     * @param target
     * @return
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(candidates);
        backTracking2(list, new ArrayList<>(), candidates, target, 0);
//        backTracking(list, new ArrayList<>(), candidates, target, 0);
        return list;
    }

    private static void backTracking2(List<List<Integer>> list, List<Integer> tempList, int[] nums, int target, int start) {
        if (target < 0) return;
        if (target == 0) {
            list.add(new ArrayList<>(tempList));
        }
        else {
            for (int i = start; i < nums.length; i++) {
                if (i > start && nums[i] == nums[i - 1]) continue;
                tempList.add(nums[i]);
                backTracking2(list, tempList, nums, target - nums[i], i + 1);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    private static void backTracking(List<List<Integer>> list, List<Integer> tempList, int[] nums, int target, int start) {
        if (target < 0) return;
        if (target == 0)
            list.add(new ArrayList<>(tempList));
        else {
            for (int i = start; i < nums.length; i++) {
                tempList.add(nums[i]);
                backTracking(list, tempList, nums, target - nums[i], i);
                tempList.remove(tempList.size() - 1);
            }
        }
    }
}
