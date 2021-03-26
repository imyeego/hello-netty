package com.imyeego.algorithm;

/**
 * @authur : liuzhao
 * @date : 3/26/21:10:48 PM
 * @des :
 */
public class Offer8 {

    public static void main(String[] args) {
//        int[] nums = {3,4,5,1,2};
        int[] nums = {1,1,1,0,1};

        System.out.println(minRotateArray(nums));
    }


    static int minRotateArray(int[] array) {
        if ((array == null)) return -1;
        if (array.length == 1) return array[0];

        int low = 0, high = array.length - 1;
        int mid = 0;

        while (array[low] >= array[high]) {
            if (high - low == 1) {
                mid = high;
                break;
            }

            mid = (low + high) >> 1;
            if (array[low] == array[mid] && array[high] == array[mid]) {
                return min(array, low, high);
            }
            if (array[mid] >= array[low]) low = mid;
            else if (array[mid] <= array[high]) high = mid;
        }

        return array[mid];
    }

    static int min(int[] array, int low, int high) {
        int result = array[low];
        for (int i = low + 1; i <= high; i++) {
            if (array[i] <= result) result = array[i];
        }


        return result;
    }
}
