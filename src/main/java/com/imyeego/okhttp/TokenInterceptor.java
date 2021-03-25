package com.imyeego.okhttp;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * @authur : liuzhao
 * @date : 2020/9/14
 * @time : 下午 3:27
 * @Des :
 */
public class TokenInterceptor implements Interceptor {

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request request = chain.request();
        String token = Token.localToken;
        if ("".equals(token)) {
            System.out.println("本地token为空");
            token = accessToken();
            System.out.println("获取token:" + token);

            Token.localToken = token;

        }
        request.newBuilder().addHeader("Authorization", token).build();
        Response response = chain.proceed(request);
        if (isTokenExpire(response)) {
            System.out.println("本地token过期");

            token = refreshToken();
            System.out.println("刷新token:" + token);

            Token.localToken = token;
            Request request1 = request.newBuilder().header("Authorization", token).build();
            response.close();
            return chain.proceed(request1);
        }

        return response;
    }

    private boolean isTokenExpire(Response response) {
        ResponseBody body = response.body();
        if (body != null){
            try {
                String resp = body.string();
                BaseResult model = new Gson().fromJson(resp, BaseResult.class);
                //4061是与后台商量的token失效后的错误码，具体应根据自己的项目决定
                if(model.getCode() == 401) {
                    return true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    private synchronized String accessToken() {
        String tokenString = OkHttpUtil.getInstance(5).get("http://localhost:8088/accessToken/1");
        if (isJson(tokenString)) {
            Token token = new Gson().fromJson(tokenString, Token.class);
            return token.getToken();
        }
        return "";
    }

    private String refreshToken() {
        String tokenString = OkHttpUtil.getInstance(5).get("http://localhost:8088/refreshToken");
        System.out.println("refresh token response:" + tokenString);
        if (isJson(tokenString)) {
            Token token = new Gson().fromJson(tokenString, Token.class);
            return token.getToken();
        }

        return "";
    }

    public static boolean isJson(String json) {
        if (isEmpty(json)) return false;
        try {
            new JsonParser().parse(json);
            return true;
        } catch (JsonParseException e) {
            return false;
        }
    }

    public static boolean isEmpty(String input) {
        if (input == null || "".equals(input)||"null".equals(input))
            return true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }
}
