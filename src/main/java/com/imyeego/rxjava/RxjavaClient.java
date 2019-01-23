package com.imyeego.rxjava;

import rx.Observable;

import java.util.LinkedList;
import java.util.List;

public class RxjavaClient {

    private static int LENGTH = 20;

    public static void main(String[] args) {
//        List<Integer> list = new LinkedList<>();
//
//        for (int i = 0; i < LENGTH; i++) {
//            Integer integer = (int)(Math.random() * 20 + 1);
//            list.add(integer);
//        }
//        print(list);
        System.out.println("----------------------------------");
//        just(list);
        String a = "ab";
        String b = "a" + "b";
        String c = new String("ab");
        System.out.println(a == c);

    }


    private static void just(List<Integer> list){
        Integer[] array = list.toArray(new Integer[list.size()]);
        Observable.from(array)
                .map(integer -> integer + 1)
                .doOnNext(System.out::println).subscribe();
    }

    private static void print(List<Integer> list){
        for (Integer integer : list){
            System.out.println(integer.intValue());
        }
    }


}
