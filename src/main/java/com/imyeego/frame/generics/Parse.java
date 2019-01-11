package com.imyeego.frame.generics;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class Parse {


    public static <T> void parse(String json, ICallback<T> callback){
        Type[] types = callback.getClass().getGenericInterfaces();
        Type type = ((ParameterizedType)types[0]).getActualTypeArguments()[0];
        Type jsonType = new ParameterizedTypeImpl(Result.class, new Type[]{type});
        Result<T> result = new Gson().fromJson(json, jsonType);
        callback.onSuccess(result.getData());
    }


}
