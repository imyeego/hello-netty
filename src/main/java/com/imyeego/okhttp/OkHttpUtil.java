package com.imyeego.okhttp;

import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @authur : liuzhao
 * @date : 2020/9/14
 * @time : 下午 3:14
 * @Des :
 */
public class OkHttpUtil {

    private OkHttpClient okHttpClient;
    private OkHttpClient okHttpClient1;

    private OkHttpUtil(){//构造方法只会走一次
        okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(new TokenInterceptor())
                .build();
    }

    private OkHttpUtil(int time){//构造方法只会走一次
        okHttpClient1 = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();
    }

    private static OkHttpUtil okHttpUtils =null;
    private static OkHttpUtil okHttpUtil =null;
    public static OkHttpUtil getInstance(){
        //双重锁
        if (okHttpUtils ==null){
            synchronized (Object.class){//class对象在内存中只有一个
                if (okHttpUtils ==null){
                    okHttpUtils = new OkHttpUtil();
                }
            }
        }
        return okHttpUtils;
    }

    public static OkHttpUtil getInstance(int time){
        //双重锁
        if (okHttpUtil ==null){
            synchronized (Object.class){//class对象在内存中只有一个
                if (okHttpUtil ==null){
                    okHttpUtil = new OkHttpUtil(time);
                }
            }
        }
        return okHttpUtil;
    }

    public void get(final String url, HttpCallback callback) {
        Request request = new Request.Builder().url(url).get().build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callback.onSuccess(response.body().string());
            }
        });
    }

    public String get(final String url) {
        Request request = new Request.Builder().url(url).addHeader("Authorization", Token.localToken).get().build();
        Call call = okHttpClient1.newCall(request);

        try {
            Response response = call.execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return "网络错误";
        }
    }

    interface HttpCallback {
        void onSuccess(String response);

        void onFailure(Throwable e);
    }
}
