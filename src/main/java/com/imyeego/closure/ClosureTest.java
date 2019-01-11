package com.imyeego.closure;

public class ClosureTest {

    public static void main(String[] args) {
        Counter counter = Counter.create();

        counter.count();
        System.out.println(counter.getCount());
        counter.count();
        System.out.println(counter.getCount());

        counter.count();
        System.out.println(counter.getCount());

        counter.count();
        System.out.println(counter.getCount());

        counter.count();
        System.out.println(counter.getCount());

        counter.count();
        System.out.println(counter.getCount());

    }
}
