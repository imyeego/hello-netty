package com.imyeego.promise;

import java.util.concurrent.*;

public class Promise<T> {
    private volatile IAction<T> iAction;
    private Callable callable;
    private Future future;
    private IError iError;
    private static ExecutorService service;


    public static <T> Promise<T> of(Callable<T> callable) {
        return new Promise<T>(callable);
    }

    private Promise(Callable<T> t){
        callable = t;
        executorService();
    }

    private synchronized ExecutorService executorService() {
        if (service == null) {
            service = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 10, TimeUnit.SECONDS,
                    new SynchronousQueue<Runnable>(), runnable -> {
                        Thread thread = new Thread(runnable);
                        thread.setName("PromiseExecutor");
                        thread.setDaemon(false);
                        return thread;
                    });
        }
        return service;
    }


    public Promise<T> then(Action<T> action) {
        iAction = new ThenAction<T>(iAction, action);
        return this;
    }

    public Promise<T> ui(Action<T> action) {
        iAction = new ThenAction<T>(iAction, action);
        return this;
    }



    public Promise<T> make() {
        Future<T> future = service.submit(callable);
        this.future = future;
        service.execute(new Loop<T>(future));

        return this;
    }



    public Promise<T> error(Err err) {
        iError = new Error(err);
        return this;
    }

    public void cancel() {
        if (!future.isCancelled())
            future.cancel(true);
        if (service != null) {
            service.shutdown();
        }
    }

    private class Loop<V> implements Runnable {
        Future<V> future;

        public Loop(Future<V> future) {
            this.future = future;
        }

        @Override
        public void run() {
            try {
                V t = future.get();
                if (t != null && iAction != null) {
                    iAction.execute((T) t);
                }

            } catch (InterruptedException | ExecutionException e) {
                if (iError != null) {
                    iError.error(e);
                }
//                e.printStackTrace();
            }
        }
    }
}
