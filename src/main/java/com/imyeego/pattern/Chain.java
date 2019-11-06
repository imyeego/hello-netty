package com.imyeego.pattern;

public class Chain {

    private IOperator operator;
    private int a;
    private IAction iAction;

    public static Chain of(int a) {
        return new Chain(a);
    }

    public static Chain start() {
        return new Chain();
    }

    private Chain(int a) {
        this.a = a;
        operator = new Head(a);
    }

    private Chain() {
        iAction = new Start();
    }

    public Chain add(int num) {
        operator = new AddOperator(operator, num);
        return this;
    }

    public Chain sub(int num) {
        operator = new SubOperator(operator, num);
        return this;
    }

    public Chain then(Action action){
        iAction = new ThenAction(iAction, action);
        return this;
    }

    public int call() {
        return operator.operate(a);
    }

    public void go() {
        iAction.execute();
    }
}
