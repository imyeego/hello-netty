package com.imyeego.frame;

import java.util.function.Supplier;

public class Lambda {

    public int s = 1;
    public static final String ss = "np";

    static {
        System.out.println("123");
    }

    public static void main(String[] args) {
//        System.out.println(ss);
        Strategy card = new VerifyWithCard();
        card.skip();
        card.next();

        Strategy db = new VerifyWithDb();
        db.skip();
        db.next();
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
