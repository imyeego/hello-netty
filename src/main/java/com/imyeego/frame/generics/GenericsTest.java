package com.imyeego.frame.generics;

import java.io.FileInputStream;

public class GenericsTest {
    public static void main(String[] args) {
        ApiService api = RealCall.create(ApiService.class);
        Call<String> call = api.getNews("I'm liuzhao");
        call.enqueue();



    }


}
