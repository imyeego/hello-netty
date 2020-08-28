package com.imyeego.promise;

public class Test {
    public static void main(String[] args) {
        Promise.of(() -> {
            Thread.sleep(3000);
            return "777";
        })
                .then(System.out::println)
                .map(Integer::parseInt)
                .then(s -> {
                    try {
                        Thread.sleep(3000);
                        System.out.println(s + 7);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                })
                .make();
    }


}
