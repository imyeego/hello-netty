package com.imyeego.pattern;

public class SubOperator implements IOperator {

    private IOperator target;
    private int num;

    public SubOperator(IOperator target, int num) {
        this.target = target;
        this.num = num;
    }

    @Override
    public int operate(int a) {
        if (target == null) {
            return 0 - num;
        }
        return target.operate(a) - num;
    }
}
