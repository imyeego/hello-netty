package com.imyeego.produceandconsume;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

public class BlockingQueueModel implements Model {

    private BlockingQueue<Task> queue;
    private AtomicInteger increTaskNo = new AtomicInteger(0);

    public BlockingQueueModel(int cap) {
        queue = new LinkedBlockingDeque<>(cap);
    }

    @Override
    public Runnable newRunnableProducer() {
        return new ProducerImpl();
    }

    @Override
    public Runnable newRunnableConsumer() {
        return new ConsumerImpl();
    }


    private class ConsumerImpl extends AbstractConsumer implements Consumer{
        @Override
        public void consume() throws InterruptedException {
            Task task = queue.take();
            Thread.sleep(500 + (long) (Math.random() * 500));
            System.out.println("consume: " + task.no);
        }
    }


    private class ProducerImpl extends AbstractProducer implements Producer{
        @Override
        public void produce() throws InterruptedException {
            Thread.sleep((long) (Math.random() * 1000));
            Task task = new Task(increTaskNo.getAndIncrement());
            queue.put(task);
            System.out.println("produce: " + task.no);
        }
    }


    public static void main(String[] args) {
        Model model = new BlockingQueueModel(3);
        for (int i = 0; i < 5; i++) {
            new Thread(model.newRunnableProducer()).start();
        }

        for (int i = 0; i < 5; i++) {
            new Thread(model.newRunnableConsumer()).start();
        }
    }
}
