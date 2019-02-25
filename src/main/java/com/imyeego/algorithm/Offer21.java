package com.imyeego.algorithm;

import java.util.Stack;

/**
 * @authur : liuzhao
 * @time : 2019/2/25
 * @email : imyeego@gmail.com
 * @description : min stack from LeetCode#155
 */
public class Offer21 {

    Stack<Integer> mData = new Stack<>();
    Stack<Integer> mMin = new Stack<>();
    public static void main(String[] args) {
        Offer21 offer21 = new Offer21();
        offer21.push(4);
        offer21.push(-3);
        offer21.push(2);
        System.out.println(offer21.min());
        System.out.println(offer21.top());

        offer21.pop();
        System.out.println(offer21.min());
    }

    public void push(int x) {
        mData.push(x);
        if (mMin.size() == 0 || x < mMin.peek()) {
            mMin.push(x);
            return;
        }
        mMin.push(mMin.peek());
    }

    public void pop() {
        mData.pop();
        mMin.pop();
    }

    public int top() {
        return mData.empty() ? 0 : mData.peek();
    }

    public int min() {
        return mMin.empty() ? 0 : mMin.peek();
    }

}
