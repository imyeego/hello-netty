package com.imyeego.algorithm;

/**
 * @author : liuzhao
 * @time : 2019/2/11
 * @description : add two nums without using arithmetic operators
 */
public class Offer47 {

    public static void main(String[] args) {
//        System.out.println(addWithout(50, 7));
//        System.out.println(addRecursive(50, 45));
//        System.out.println(subtractRecursive(30, 45));
        System.out.println(multi1(3, 567));
    }

    private static int addIterative(int num1, int num2){
        int temp;
        while (num2 != 0){
            temp = num1 ^ num2; // 无进位相加
            num2 = (num1 & num2) << 1; // 进位值
            num1 = temp;
        }
        return num1;
    }


    private static int addRecursive(int num1, int num2){
        return num2 == 0 ? num1 : addRecursive(num1 ^ num2, (num1 & num2) << 1);
    }

    private static int subtractRecursive(int num1, int num2){
        return addRecursive(num1, addRecursive(~num2, 1));

    }

    private static int multi(int a, int b){
        int res = 0;
        for (int i = 0; i < b; i = addIterative(i, 1)) {
            res = addIterative(res, a);
        }
        return res;
    }

    private static int multi1(int a, int b){
        int res = 0;
        while (a != 0){
            if ((a & 1) != 0) {
                res += b;
            }
            a >>= 1;
            b <<= 1;
        }
        return res;
    }
}
