package com.imyeego.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * @Authur: liuzhao
 * @Date: 2019/2/7 0007 22:28
 * @Email: imyeego@gmail.com
 * @Description:
 */
public class Offer5 {

    static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = null;


//        tailToHeadUsingRecursive(node1).stream().forEach(System.out::println);
        tailToHeadUsingIterative(node1).stream().forEach(System.out::println);
    }

    private static List<Integer> tailToHeadUsingRecursive(ListNode head){

        if (head != null){
            tailToHeadUsingRecursive(head.next);
            list.add(head.val);
        }
        return list;
    }

    private static List<Integer> tailToHeadUsingIterative(ListNode head){
        Stack<Integer> stack = new Stack<>();
        int count = 0;
        while (head != null) {
            stack.push(head.val);
            head = head.next;
            count ++;
        }
        for (int i = 0; i < count; i++) {
            list.add(stack.pop());
        }
        return list;
    }

}
