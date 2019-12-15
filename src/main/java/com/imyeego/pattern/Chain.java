package com.imyeego.pattern;

public class Chain<T> {

    private IOperator operator;
    private int a;
    private IAction iAction;
    private OnSubscribe<T> onSubscribe;
    T t;

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



    public Chain(OnSubscribe<T> onSubscribe) {
        this.onSubscribe = onSubscribe;
    }

    private Chain() {
        iAction = new Start();
    }

    public static <T> Chain<T> of(T t) {
        return new Chain<>(t, new Empty<T>());
    }

    private Chain(T t, OnSubscribe<T> f) {
        this.onSubscribe = f;
        this.t = t;
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

    public Chain<T> then(Action1<T> action1) {
        return create(new OnSubscribeThen<T>(this, action1));
    }

    private static <T> Chain<T> create(OnSubscribe<T> f) {
        return new Chain<T>(f);
    }

    public int call() {
        return operator.operate(a);
    }

    public void go() {
        iAction.execute();
    }

    public Chain<T> execute() {
        if (onSubscribe != null) {
            onSubscribe.call(t);
        }
        return this;
    }

    public interface OnSubscribe<T> {
        void call(T t);
    }

    static class Empty<T> implements OnSubscribe<T> {
        @Override
        public void call(T t) {

        }
    }
}
