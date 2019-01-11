package com.imyeego.produceandconsume;

public interface Model {
    Runnable newRunnableProducer();
    Runnable newRunnableConsumer();
}
