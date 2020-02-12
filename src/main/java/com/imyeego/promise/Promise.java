package com.imyeego.promise;

import rx.schedulers.Schedulers;

import java.util.concurrent.*;

public class Promise<T> {
    private final OnSubscribe<T> onSubscribe;

    public static <T> Promise<T> of(Callable<T> callable) {
        return create(new OfOnSubscribe<>(callable));
    }

    private Promise(OnSubscribe<T> onSubscribe) {
        this.onSubscribe = onSubscribe;
    }


    public Promise<T> then(Action<T> action) {
        return create(new ThenOnSubscribe<>(this, action));
    }

    public <R> Promise<R> map(Func<? super T, ? extends R> func) {
        return create(new MapOnSubscribe<T, R>(this, func));
    }

    private static <T> Promise<T> create(OnSubscribe<T> f) {
        return new Promise<T>(f);
    }

    public Promise<T> make() {
        return make(new Empty<>());
    }

    public Promise<T> make(Subscriber<? super T> subscriber) {
        this.onSubscribe.call(subscriber);
        return this;
    }

    public void cancel() {

    }

    public interface OnSubscribe<T> extends Action<Subscriber<? super T>> {
        // cover for generics insanity
    }

    static final class Empty<T> implements Subscriber<T> {
        @Override
        public void onNext(T t) {

        }

        @Override
        public void onError(Throwable e) {

        }
    }

}
