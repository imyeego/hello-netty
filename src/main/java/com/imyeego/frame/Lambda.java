package com.imyeego.frame;

import java.util.function.Supplier;

public class Lambda {

    public static void main(String[] args) {

    }



    private static void lambda(){
        final int[] answer = {0};
        new Thread(() -> {
            answer[0]++;
            System.out.println(answer[0]);
        }).start();
    }

    private static void lambda1(){
        int myVar = 42;
        Supplier<Integer> lambdaFun = () -> myVar; // error
//        myVar++;
//        System.out.println(lambdaFun.get());
    }
}
