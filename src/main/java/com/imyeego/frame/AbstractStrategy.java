package com.imyeego.frame;

public abstract class AbstractStrategy implements Strategy {
    protected int i;

    @Override
    public void skip() {
        i ++;
        next();
    }
}
