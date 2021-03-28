package com.imyeego.algorithm;

/**
 * @authur : liuzhao
 * @date : 3/28/21:3:53 PM
 * @des :
 */
public class Offer32 {

    public static void main(String[] args) {
        int i = 123;
        System.out.println(numberOf1Between2(i));
    }

    static int numberOf1Between(int n) {
        if (n <= 0) return 0;
        int count = 0;
        for (int i = 1; i <= n; i++) {
            count += countOf1(i);
        }

        return count;
    }

    private static int countOf1(int i) {
        int count = 0;
        while (i != 0) {
            if (i % 10 == 1) count++;
            i = i / 10;
        }

        return count;
    }

    static int numberOf1Between2(int n) {
        if (n <= 0) return 0;

        int count = 0;
        int current;
        int base = 1;
        int retain = 0;
        while (n != 0) {

            current = n % 10;
            n = n / 10;

            if (current > 1) {
                count += (n + 1) * base;
            } else if (current == 1) {
                count += n * base + retain + 1;
            } else {
                count += n * base;
            }
            retain += current * base;
            base *= 10;
        }


        return count;
    }
}
