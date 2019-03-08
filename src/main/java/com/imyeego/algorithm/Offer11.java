package com.imyeego.algorithm;

/**
 * @authur : liuzhao
 * @time : 2019/3/8
 * @email : imyeego@gmail.com
 * @description : power(x, n)
 */
public class Offer11 {

    public static void main(String[] args) {
        System.out.println(pow(2.0 , 1));
    }

    /**
     * 6000+ms too slow
     * @param x
     * @param n
     * @return
     */
    private static double power(double x, int n) {
        double res = 1;
        if (x == 0.0 && n < 0) return 0.0;
        if (n == Integer.MIN_VALUE && (Math.abs(x) != 1)) return 0.0;
        for (int i = 0; i < Math.abs(n); i++) {
            res *= x;
        }
        return n >= 0 ? res : 1 / res;
    }

    /**
     * 6ms faster than 100%
     * @param x
     * @param n
     * @return
     */
    private static double pow(double x, int n) {
        if (n == Integer.MIN_VALUE) {
            if (Math.abs(x) == 1) return 1.0;
            else return 0.0;
        }

        if (n < 0) {
            n = -n;
            x = 1 / x;
        }
        double res = 1;
        while (n != 0) {
            if ((n & 1) == 1) res *= x;
            x *= x;
            n >>= 1;
        }
        return res;
    }


}
