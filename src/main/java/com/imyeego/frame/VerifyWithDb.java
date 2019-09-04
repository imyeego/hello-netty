package com.imyeego.frame;

public class VerifyWithDb extends AbstractStrategy {

    @Override
    public void next() {
        System.out.println("数据库验证" + i);
    }
}
