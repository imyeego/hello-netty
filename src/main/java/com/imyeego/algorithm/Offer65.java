package com.imyeego.algorithm;

import java.util.*;

/**
 * @authur : liuzhao
 * @time : 2019/2/20
 * @email : imyeego@gmail.com
 * @description : sliding window max
 */
public class Offer65 {

    public static void main(String[] args) {
        int[] nums = {2,3,4,2,6,2,5,1};
        int[] res = maxSlidingWindow(nums, 3);
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }
    }

    /**
     * from LeetCode#239
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0 || k == 0) return new int[]{};
        int[] res = new int[nums.length - k + 1];
        Deque<Integer> deque = new LinkedList<>();
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            while (!deque.isEmpty() && deque.peek() < j - k + 1) {
                deque.poll();
            }

            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[j]) {
                deque.pollLast();
            }
            deque.offer(j);
            if (j >= k - 1) {
                res[i ++] = nums[deque.peek()];
            }

        }
        return res;
    }
}
