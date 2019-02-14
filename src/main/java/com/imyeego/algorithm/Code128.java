package com.imyeego.algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @authur : liuzhao
 * @time : 2019/2/14
 * @email : imyeego@gmail.com
 * @description : longest consecutive sequence
 */
public class Code128 {

    public static void main(String[] args) {
        int[] nums = {4, 1, 100, 5, 200, 2, 3};
        System.out.println(longestConsecutive1(nums));
    }

    public static int longestConsecutive(int[] nums) {
        if (nums.length == 1) return 1;
        Arrays.sort(nums);
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int result = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) dp[i] = dp[i - 1];
            if (nums[i] == nums[i - 1] + 1){
                dp[i] = dp[i - 1] + 1;
            }
            result = Math.max(result, dp[i]);
        }
        return result;
    }

    public static int longestConsecutive1(int[] nums) {
        Map<Integer, Boolean> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, true);
        }
        int result = 0;
        for (int num : nums) {
            int length = 1, temp = num + 1;
            while (map.get(temp) != null) {
                map.remove(temp);
                temp ++;
                length ++;
            }
            temp = num - 1;
            while (map.get(temp) != null) {
                map.remove(temp);
                temp --;
                length ++;
            }

            result = Math.max(result, length);
        }
        return result;

    }
}
