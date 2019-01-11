package com.imyeego.frame.generics;

public interface ApiService {

    Call<String> getNews(String text);

    @GET("/toutiao/index")
    Call<String> getMovie(@Query("type")String type, @Query("key")String key);
}
