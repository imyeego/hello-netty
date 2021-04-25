package com.imyeego.algorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @authur : liuzhao
 * @date : 4/10/21:9:23 AM
 * @des : 括号匹配
 */
public class Code20 {

    public static void main(String[] args) {
        String s = "()[]{}";
        System.out.println(isValid(s));
    }

    static boolean isValid(String s) {
        if (s == null || s.length() == 1) return false;
        Map<Character, Character> map = new HashMap<>();
        map.put(')','(');
        map.put(']','[');
        map.put('}','{');
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (!map.containsKey(c)) stack.push(c);
            else if (stack.isEmpty() || !map.get(c).equals(stack.pop())) return false;
        }

        return stack.isEmpty();
    }
}
