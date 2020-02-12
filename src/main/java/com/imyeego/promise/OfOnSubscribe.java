package com.imyeego.promise;

import java.util.concurrent.*;

public class OfOnSubscribe<T> implements Promise.OnSubscribe<T> {

    final Callable<T> callable;

    public OfOnSubscribe(Callable<T> callable) {
        this.callable = callable;
    }

    @Override
    public void call(Subscriber<? super T> subscriber) {
        Future<T> future = Schedulers.instance().submit(callable);
        Schedulers.instance().execute(new SubscriberFutureRunnable<T>(subscriber, future));
    }

    static class SubscriberFutureRunnable<T> implements Runnable {
        final Subscriber<? super T> subscriber;
        final Future<T> future;

        public SubscriberFutureRunnable(Subscriber<? super T> subscriber, Future<T> future) {
            this.subscriber = subscriber;
            this.future = future;
        }

        @Override
        public void run() {
            try {
                T t = future.get();
                if (t != null) {
                    subscriber.onNext(t);
                }
            } catch (InterruptedException | ExecutionException e) {
                subscriber.onError(e);
            }
        }
    }

    static class Schedulers {
        static volatile ExecutorService service;

        static ExecutorService instance() {
            if (service == null) {
                synchronized (Schedulers.class) {
                    if (service == null) {
                        service = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 10, TimeUnit.SECONDS,
                                new SynchronousQueue<Runnable>(), runnable -> {
                            Thread thread = new Thread(runnable);
                            thread.setName("PromiseExecutor");
                            thread.setDaemon(false);
                            return thread;
                        });
                    }
                }
            }

            return service;
        }
    }
}
