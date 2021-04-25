package com.imyeego.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @authur : liuzhao
 * @date : 4/11/21:2:46 PM
 * @des :
 */
public class Offer28 {

    public static void main(String[] args) {

    }

    static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 1) {
            List<Integer> list = new ArrayList<>();
            list.add(nums[0]);
            res.add(list);
        } else {
            int[] base = {};
            permute1(res, base, nums);
        }
        return res;
    }

    static void permute1(List<List<Integer>> res, int[] base, int[] nums) {
        if (nums.length == 0) {
            res.add(toList(base));
        } else {

        }
    }

    static List<Integer> toList(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int i : nums)
            list.add(i);
        return list;
    }
}
