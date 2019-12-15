package com.imyeego.pattern;

import rx.schedulers.Schedulers;

public class Test {

    public static void main(String[] args) {
//        int result = Chain.of(7).add(5).sub(2).add(19).call();
//        System.out.println(result);
//
//        Chain.start()
//                .then(() -> System.out.println("first"))
//                .then(() -> System.out.println("second"))
//                .go();

        Chain.of("liuzhao")
                .then(s -> System.out.println("hello " + s))
                .then(s -> System.out.println(s + " is a boy"))
                .execute();

    }
}
