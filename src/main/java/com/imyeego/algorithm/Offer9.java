package com.imyeego.algorithm;

/**
 * @authur : liuzhao
 * @time : 2019/2/18
 * @email : imyeego@gmail.com
 * @description : Fibonacci sequence
 */
public class Offer9 {

    public static void main(String[] args) {
        System.out.println(fibonacci(30));
    }

    private static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }

    /**
     * 迭代实现 time complexity: O(n)
     * from Offer#9 & LeetCode#509
     * @param n
     * @return
     */
    private static int fibonacciIterative(int n) {
        if(n <= 1) {
            return n;
        }
        long one = 0;
        long two = 1;
        long res = 0;

        for(int i = 2; i <= n; i++)
        {
            res = one + two;

            one = two;
            two = res;
        }

        return (int) res;
    }
}
