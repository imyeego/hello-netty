package com.imyeego.frame.concurrent_modification_exception;

import java.util.concurrent.*;

public class CountDownLatchTest {

    public static final int RUNNER_COUNT = 10;

    public static void main(String[] args) throws InterruptedException{
//        testCountDownLatch();
//        testParallel();
        testCyclicBarrier();
        
    }


    private static void testCountDownLatch() throws InterruptedException{
        final CountDownLatch end = new CountDownLatch(RUNNER_COUNT);

        final ExecutorService service = Executors.newFixedThreadPool(10);

        for (int i = 0; i < RUNNER_COUNT; i++) {
            int num = i + 1;
            service.execute(() -> {
                try {
                    Thread.sleep((long)(Math.random() * 10_000));
                    System.out.println("No." + num + " arrived!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    end.countDown();
                }
            });

        }
        System.out.println("Game started!");
        end.await();
        System.out.println("Game over!");
        service.shutdown();
    }

    private static void testParallel() {
        final CountDownLatch begin = new CountDownLatch(2);
        final ExecutorService service = Executors.newFixedThreadPool(4);

        Future<Integer> future = service.submit(() -> {
            Thread.sleep(5_000);
            return 5;
        });

        Future<Integer> future1 = service.submit(() -> {
            Thread.sleep(8_000);
            return 3;
        });
        long start = System.currentTimeMillis();
        try {
            int value = future.get() + future1.get();
            System.out.println("结果为:" + value + " cost time " + (System.currentTimeMillis() - start) + "ms");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            service.shutdown();
        }

    }

//    static class Task<T> implements Runnable {
//        Future<T> future;
//
//        public Task(Future<T> future) {
//            this.future = future;
//        }
//
//        @Override
//        public void run() {
//            T t;
//            t = future.get();
//        }
//    }


    static void testCyclicBarrier() {
        Object object = new Object();
        object.hashCode();
        final ExecutorService service = Executors.newFixedThreadPool(10);
        System.out.println("Game started!");

        final CyclicBarrier cyclicBarrier = new CyclicBarrier(RUNNER_COUNT, () -> {
            System.out.println("Game over!");
            service.shutdown();
        });

        for (int i = 0; i < RUNNER_COUNT; i++) {
            int num = i + 1;
            service.execute(() -> {
                try {
                    Thread.sleep((long)(Math.random() * 10_000));
                    System.out.println("No." + num + " arrived!");
                    cyclicBarrier.await();

                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });

        }
    }
}
