package com.imyeego.frame.generics;

public class OkCall<T> implements Call<T> {

    private String msg;

    public OkCall(Object[] args){
        msg = args[0].toString();
    }
    @Override
    public void enqueue() {
        System.out.println(msg);
    }
}
