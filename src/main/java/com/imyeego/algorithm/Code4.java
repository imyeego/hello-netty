package com.imyeego.algorithm;

/**
 * @Authur: liuzhao
 * @Date: 2019/2/7 0007 12:09
 * @Email: imyeego@gmail.com
 * @Description: median of two sorted arrays
 */
public class Code4 {

    public static void main(String[] args) {
        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};

        double median = findMedianSortedArrays(nums1, nums2);
        System.out.println(median);
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int lengthA = nums1.length;
        int lengthB = nums2.length;
        int length = lengthA + lengthB;
        if ((length & 0x1) == 0) {
            return (findKth(nums1, nums2, 0, 0, length / 2)
                    + findKth(nums1, nums2, 0, 0, length / 2 + 1)) / 2.0;
        }
        return findKth(nums1, nums2, 0, 0, length / 2 + 1);
    }

    public static double findKth(int[] nums1, int[] nums2, int startA, int startB, int k) {
        int lengthA = nums1.length;
        int lengthB = nums2.length;

        if (startA >= lengthA) return nums2[startB + k - 1];
        if (startB >= lengthB) return nums1[startA + k - 1];

        if (k == 1) return Math.min(nums1[startA], nums2[startB]);

        int mid = k / 2 - 1;
        int pointA = startA + mid >= lengthA ? Integer.MAX_VALUE : nums1[startA + mid];
        int pointB = startB + mid >= lengthB ? Integer.MAX_VALUE : nums2[startB + mid];

        if (pointA > pointB) {
            return findKth(nums1, nums2, startA, startB + k/2, k - k/2);
        } else {
            return findKth(nums1, nums2, startA + k/2, startB, k - k/2);

        }

    }
}
