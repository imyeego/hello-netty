package com.imyeego.algorithm;

import java.util.Stack;

/**
 * @authur : liuzhao
 * @time : 2019/2/25
 * @email : imyeego@gmail.com
 * @description : Max Stack
 */
public class Code716 {

    private int max;
    private Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) {
        Code716 code716 = new Code716();
        code716.push(4);
        code716.push(-3);
        code716.push(2);
        code716.push(1);
        code716.push(5);
        System.out.println(code716.getMax());
        code716.pop();
        System.out.println(code716.top());
    }

    public void push(int x) {
        if (x >= max) {
            stack.push(max);
            max = x;
        }
        stack.push(x);
    }

    public void pop() {
        if (stack.pop() == max) max = stack.pop();
    }

    public int top() {
        return stack.empty() ? -1 : stack.peek();
    }

    public int getMax() {
        return max;
    }
}
