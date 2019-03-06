package com.imyeego.algorithm;

/**
 * @authur : liuzhao
 * @time : 2019/3/5
 * @email : imyeego@gmail.com
 * @description : implement strStr() from LeetCode#28
 */
public class Code28 {

    public static void main(String[] args) {
        String hayStack = "aabaaabaaac";
        String needle = "aabaaac";
        System.out.println(kmp(hayStack, needle));

    }

    public static int strStr(String haystack, String needle) {
        if (needle == null || haystack == null) return -1;
        if (needle.equals("")) return 0;
        if (haystack.length() < needle.length()) return -1;
        int j = 0, i = 0;
        char[] array = haystack.toCharArray();
        char[] chars = needle.toCharArray();

        while (i < array.length && j < chars.length) {
            if (array[i] == chars[j]) {
                i ++;
                j ++;
            } else {
                i = i - j + 1;
                j = 0;
            }
        }

        return j == chars.length ? i - j : -1;
    }

    private static int kmp(String haystack, String needle) {
        if (needle == null || haystack == null) return -1;
        if (needle.equals("")) return 0;
        if (haystack.length() < needle.length()) return -1;
        int j = 0, i = 0;
        char[] array = haystack.toCharArray();
        char[] chars = needle.toCharArray();
        int[] next = next(needle);

        while (i < array.length && j < chars.length) {
            if (j == -1 || array[i] == chars[j]) {
                i ++;
                j ++;
            } else {
                j = next[j];
            }
        }

        return j == chars.length ? i - j : -1;
    }


    private static int[] next(String needle) {
        char[] chars = needle.toCharArray();
        int[] next = new int[chars.length];

        next[0] = -1;
        int i = 0, k = -1;
        while (i < chars.length - 1) {
            if (k == -1 || chars[i] == chars[k]) {
                next[++i] = ++k;
            } else {
                k = next[k];
            }
        }

        return next;
    }
}
