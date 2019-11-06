package com.imyeego.pattern;

public class AddOperator implements IOperator {

    private IOperator target;
    private int num;

    public AddOperator(IOperator target, int num) {
        this.target = target;
        this.num = num;
    }

    @Override
    public int operate(int a) {
        if (target == null) {
            return num;
        }
        return target.operate(a) + num;
    }
}
