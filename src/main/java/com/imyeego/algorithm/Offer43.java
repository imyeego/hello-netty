package com.imyeego.algorithm;

import java.util.Stack;

/**
 * @authur : liuzhao
 * @date : 3/24/21:5:06 PM
 * @des : 反转单词顺序：input:I am a student, output:student a am I
 */
public class Offer43 {


    public static void main(String[] args) {

        String str = "I am a student";

        System.out.println(reverseWord2(str));
    }


    static String reverseWord(String str) {
        if (str == null || str.length() <= 1) return str;
        StringBuilder sb = new StringBuilder();
        char[] chars = str.toCharArray();
        Stack<String> stack = new Stack<>();
        StringBuilder temp = new StringBuilder();

        for (char c : chars) {
            if (c != ' ') temp.append(c);
            else {
                stack.push(temp.toString());
                temp = new StringBuilder();
            }
        }
        stack.push(temp.toString());
        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(' ');
        }

        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    static String reverseWord2(String str) {
        if (str == null || str.length() <= 1) return str;
        String res = "", temp = "";
        char[] chars = str.toCharArray();
        for (char c : chars) {
            if (c != ' ') temp += c;
            else {
                res = " " + temp + res;
                temp = "";
            }
        }
        res = temp + res;


        return res;
    }


}
