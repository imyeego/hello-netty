package com.imyeego.algorithm;

import java.util.Stack;

/**
 * @authur : liuzhao
 * @time : 2019/3/4
 * @email : imyeego@gmail.com
 * @description : the queue implemented by two stack
 */
public class Offer7 {

    public static void main(String[] args) {
        Queue queue = new Queue();

        queue.push(2);
        queue.push(3);
        queue.push(1);
        queue.push(6);
        queue.push(8);
        System.out.println(queue.pop());
        System.out.println(queue.pop());
    }

    static class Queue {

        private Stack<Integer> mData;
        private Stack<Integer> mCache;

        public Queue() {
            mData = new Stack<>();
            mCache = new Stack<>();
        }

        public void push(int val) {
            mData.push(val);
        }

        public int pop() {
            if (empty()) return -1;
            if (mCache.empty()) {
                while (!mData.empty()) {
                    mCache.push(mData.pop());
                }
            }
            int val = mCache.pop();
            return val;
        }

        public boolean empty() {
            return mData.empty() && mCache.empty();
        }
    }
}
