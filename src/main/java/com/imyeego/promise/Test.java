package com.imyeego.promise;

public class Test {
    public static void main(String[] args) {
        Promise.of(() -> {
            Thread.sleep(5000);
            return "liuzhao";
            })
                .then(System.out::println)
                .then(s -> {
                    try {
                        Thread.sleep(3000);
                        System.out.println(s + " after");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                })
                .make();
    }
}
