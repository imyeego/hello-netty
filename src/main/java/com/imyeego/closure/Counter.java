package com.imyeego.closure;


/**
 * 基于闭包的计数器工具类，需要考虑多线程安全问题
 */
public class Counter {
    private int count;
    private ActiveCounter activeCounter;

    private Counter(){
        count = 0;
    }

    public Counter(int count) {
        this.count = count;
    }

    public static Counter create(){
        return new Counter();
    }

    public int getCount() {
        return count;
    }

    private ActiveCounter getCounter(){
        if (activeCounter == null){
            activeCounter = new ActiveCounter();
        }
        return activeCounter;
    }

    public void count(){
        getCounter().count();
    }

    public void clear(){
        count = 0;
    }

    class ActiveCounter implements CountExecute{
        @Override
        public void count() {
            count++;
        }
    }

    interface CountExecute {
        void count();
    }

}
