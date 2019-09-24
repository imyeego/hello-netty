package com.imyeego.frame;

public class SingleTon {

    static {
        System.out.println("SingleTon init");
    }
    private static SingleTon singleTon = new SingleTon();

    public static int count1;
    public static int count2 = 0;
    public static final int count3 = 3;

    static {
        count1 ++;
        count2 ++;
    }


    private SingleTon() {
        count1++;
        count2++;
    }

    public static SingleTon getInstance() {
        return singleTon;
    }
}
