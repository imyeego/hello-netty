package com.imyeego.frame.generics;

import com.imyeego.App;
import com.imyeego.frame.bean.Student;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class GenericsTest {
    public static void main(String[] args) {
        ApiService api = RealCall.create(ApiService.class);
        Call<String> call = api.getNews("I'm liuzhao");
        call.enqueue();



    }

    private static void testGeneric() {
        List<? super Fruit> list = new ArrayList<>();
        list.add(new Apple());

    }

    private static void from(List<? extends Apple> list) {
        list.stream().forEach(fruit -> {});
    }


    static class Fruit{}
    static class Apple extends Fruit{}
    static class Banana extends Fruit{}


}
