package com.imyeego.algorithm;

/**
 * @authur : liuzhao
 * @time : 2019/3/1
 * @email : imyeego@gmail.com
 * @description :
 */
public class Code7 {
    public static void main(String[] args) {
        System.out.println(reverse(-2140));
    }

    public static int reverse(int x) {
        long res = 0;
        while (x != 0) {
            res = res * 10 + x % 10;
            x /= 10;
        }
        return res > Integer.MAX_VALUE || res < Integer.MIN_VALUE ? 0 : (int) res;
    }
}
