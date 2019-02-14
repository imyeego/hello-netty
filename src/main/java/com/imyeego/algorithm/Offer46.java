package com.imyeego.algorithm;

/**
 * @authur : liuzhao
 * @time : 2019/2/14
 * @email : imyeego@gmail.com
 * @description : calculate 1 + 2 + ... + n
 */
public class Offer46 {

    public static void main(String[] args) {
        int res = sumRecursion(5);
        System.out.println(res);
    }

    private static int sumRecursion(int n){
        int res = n;
        boolean flag = (n > 0) && (res += sumRecursion(n - 1)) > 0;
        return res;
    }
}
