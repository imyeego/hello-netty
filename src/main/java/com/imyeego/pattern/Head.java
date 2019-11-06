package com.imyeego.pattern;

public class Head implements IOperator {
    private int a;

    public Head(int a) {
        this.a = a;
    }

    @Override
    public int operate(int a) {
        return a;
    }
}
