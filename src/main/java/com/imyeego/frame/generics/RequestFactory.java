package com.imyeego.frame.generics;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

public class RequestFactory {


    static RequestFactory parse(Method method){
        Type returnType = method.getGenericReturnType();
        return null;
    }
}
