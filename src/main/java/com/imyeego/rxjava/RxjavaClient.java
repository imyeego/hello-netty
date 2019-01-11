package com.imyeego.rxjava;

import rx.Observable;

import java.util.LinkedList;
import java.util.List;

public class RxjavaClient {

    private static int LENGTH = 20;

    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();

        for (int i = 0; i < LENGTH; i++) {
            Integer integer = (int)(Math.random() * 20 + 1);
            list.add(integer);
        }
//        print(list);
        System.out.println("----------------------------------");
//        just(list);

    }


    private static void just(List<Integer> list){
        Integer[] array = list.toArray(new Integer[list.size()]);
        Observable.from(array)
                .map(integer -> integer.intValue() + 1)
                .doOnNext(integer -> {
                    System.out.println(integer);
                }).subscribe();
    }

    private static void print(List<Integer> list){
        for (Integer integer : list){
            System.out.println(integer.intValue());
        }
    }


}
