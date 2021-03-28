package com.imyeego.algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * @authur : liuzhao
 * @date : 3/27/21:11:13 PM
 * @des :
 */
public class Offer35 {

    public static void main(String[] args) {
        int[] nums = {1,1,5,4,2,4,5};
        System.out.println(firstOnce1(nums));
    }

    static int firstOnce(int[] nums) {
        if (nums.length == 1) return 0;
        int res = -1;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length - 1; i++) {
            if (set.contains(i)) continue;
            res = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    set.add(j);
                    res = -1;
                    break;
                }
            }

        }


        return res;
    }

    static int firstOnce1(int[] nums) {
        if (nums.length == 0) return 0;
        int res = -1;
        int[] temp = new int[10];

        for (int i = 0; i < nums.length; i++) {
            temp[nums[i]] ++;
        }

        for (int i = 0; i < nums.length; i++) {
            if (temp[nums[i]] == 1) {
                res = i;
                break;
            }
        }


        return res;
    }
}
