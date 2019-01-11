package com.imyeego.frame.generics;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class RealCall {

    public static <T> T create(final Class<T> service){

        return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class<?>[]{service},
                (proxy, method, args) -> {
                    return call(new CallAdapter<Call<?>>() {
                        @Override
                        public <R> Call<?> adapt(Call<R> call) {
                            return call;
                        }
                    }, args);
                });
    }

    private static Object call(CallAdapter<?> callAdapter, Object[] args){
        return callAdapter.adapt(new OkCall<>(args));
    }
}
