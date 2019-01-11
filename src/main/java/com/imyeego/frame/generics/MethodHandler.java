package com.imyeego.frame.generics;

import java.lang.reflect.Method;

public class MethodHandler {

    private final RequestFactory requestFactory;

    private MethodHandler(RequestFactory requestFactory){
        this.requestFactory = requestFactory;
    }
    static MethodHandler create(Method method){
        RequestFactory requestFactory = RequestFactory.parse(method);
        return new MethodHandler(requestFactory);
    }
}
