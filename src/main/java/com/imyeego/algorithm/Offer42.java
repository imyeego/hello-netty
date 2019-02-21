package com.imyeego.algorithm;

/**
 * @authur : liuzhao
 * @time : 2019/2/21
 * @email : imyeego@gmail.com
 * @description : rotate the string
 */
public class Offer42 {

    public static void main(String[] args) {
        System.out.println(leftRotate1("abcXYZdef", 13));
    }

    private static String leftRotate(String str, int k) {
        if (str == null || str.isEmpty()) return "";
        if (k > str.length()) k %= str.length();

        char[] chars = str.toCharArray();
        char[] temp = new char[k];
        for (int i = 0; i < str.length(); i++) {
            if (i >= k) {
                chars[i - k] = chars[i];
            } else {
                temp[i] = chars[i];
            }
        }
        for (int i = 0; i < k; i++) {
            chars[str.length() - k + i] = temp[i];
        }
        return new String(chars);
    }

    private static String leftRotate1(String str, int k) {
        if (str == null || str.isEmpty()) return "";
        if (k > str.length()) k %= str.length();
        int length = str.length();
        str += str;
        return str.substring(k, k + length);
    }
}
