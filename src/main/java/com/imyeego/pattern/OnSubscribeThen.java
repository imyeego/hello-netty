package com.imyeego.pattern;

public class OnSubscribeThen<T> implements Chain.OnSubscribe<T> {

    Chain<T> source;
    Action1<T> action;

    public OnSubscribeThen(Chain<T> source, Action1<T> action) {
        this.source = source;
        this.action = action;
    }

    @Override
    public void call(T t) {
        source.execute();
        action.call(t);
    }
}
