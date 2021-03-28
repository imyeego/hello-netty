package com.imyeego.algorithm;

import java.util.Arrays;

/**
 * @authur : liuzhao
 * @time : 2019/3/18
 * @email : imyeego@gmail.com
 * @description : find out the number count over half of length in array
 */
public class Offer29 {

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 2, 2, 2, 5, 4, 2};
        System.out.println(findMoreThanHalf2(array));
    }

    private static int findMoreThanHalf(int[] array) {
        if (array.length == 0) return 0;
        if (array.length == 1) return array[0];
        Arrays.sort(array);
        int res = 0, count = 1;
        for (int i = 0; i < array.length - 1; i ++) {
            if (array[i] == array[i + 1]) {
                count ++;
            }else {
                count = 1;
            }
            if (count > array.length / 2) {
                res =  array[i];
                break;
            }
        }
        return res;
    }

    /**
     * 思想哨兵
     * @param array
     * @return
     */
    private static int findMoreThanHalf1(int[] array) {
        if (array.length == 0) return 0;
        if (array.length == 1) return array[0];
        int res = array[0], count = 1;
        for (int i = 1; i < array.length; i++) {
            if (array[i] == res) count ++;
            else count --;
            if (count == 0) {
                res = array[i];
                count = 1;
            }
        }
        count = 0;
        for (int num : array) {
            if (num == res) count ++;
        }

        return count > array.length / 2 ? res : 0;
    }

    private static int findMoreThanHalf2(int[] array) {
        if (array.length == 0) return 0;
        if (array.length == 1) return array[0];
        int res = findKth(array, 0, array.length - 1, array.length / 2);
        int count = 0;
        for (int num : array) {
            if (num == res) count ++;
        }

        return count > array.length / 2 ? res : 0;
    }

    public static int findKth(int[] array, int left, int right, int k) {
        if (left == right) return array[left];
        int pivotIndex = QuickSort.partition(array, left, right);
        if (pivotIndex > k) {
            return findKth(array, left, pivotIndex - 1, k);
        } else if (pivotIndex < k) {
            return findKth(array, pivotIndex + 1, right, k);
        } else
            return array[pivotIndex];
    }
}
