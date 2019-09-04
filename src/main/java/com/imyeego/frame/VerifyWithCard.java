package com.imyeego.frame;

public class VerifyWithCard extends AbstractStrategy {

    @Override
    public void next() {
        System.out.println("身份证验证" + i);
    }
}
