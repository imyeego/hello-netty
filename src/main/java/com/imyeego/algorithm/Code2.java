package com.imyeego.algorithm;

import java.util.Stack;

/**
 * @authur : liuzhao
 * @time : 2019/2/25
 * @email : imyeego@gmail.com
 * @description : add two numbers from LeetCode#2
 */
public class Code2 {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(9);
//        ListNode node2 = new ListNode(4);
//        ListNode node3 = new ListNode(3);

//        node1.next = node2;
//        node2.next = node3;
//        node3.next = null;

        ListNode node1_ = new ListNode(1);
        ListNode node2_ = new ListNode(9);
        ListNode node3_ = new ListNode(9);
        ListNode node4_ = new ListNode(9);
        ListNode node5_ = new ListNode(9);
        ListNode node6_ = new ListNode(9);
        ListNode node7_ = new ListNode(9);
        ListNode node8_ = new ListNode(9);
        ListNode node9_ = new ListNode(9);
        ListNode node10_ = new ListNode(9);

        node1_.next = node2_;
        node2_.next = node3_;
        node3_.next = node4_;
        node4_.next = node5_;
        node5_.next = node6_;
        node6_.next = node7_;
        node7_.next = node8_;
        node8_.next = node9_;
        node9_.next = node10_;
        node10_.next = null;
        ListNode root = addTwoNumbers1(node1, node1_);
        while (root != null) {
            System.out.println(root.val);
            root = root.next;
        }

    }

    /**
     * 此方法不被leetcode accepted,原因在于数据溢出
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(0);
        ListNode cur = res;
        long a = 0L, b = 0L, c;
        Stack<Integer> stack = new Stack<>();
        while (l1 != null) {
            stack.push(l1.val);
            l1 = l1.next;
        }
        while (!stack.empty()) {
            a = a * 10 + stack.pop();
        }

        while (l2 != null) {
            stack.push(l2.val);
            l2 = l2.next;
        }
        while (!stack.empty()) {
            b = b * 10 + stack.pop();
        }
        c = a + b;
        while (c / 10 != 0) {
            ListNode node = new ListNode((int) (c % 10));
            cur.next = node;
            cur = node;
            c /= 10;
        }
        ListNode node = new ListNode((int) c);
        cur.next = node;
        return res.next;
    }

    /**
     * 采用两数相加的逐位相加-进位处理的算法
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(0);
        ListNode cur = res;
        int carry = 0; // 进位项
        while (l1 != null || l2!= null || carry != 0) {
            ListNode node = new ListNode(0);
            int sum = ((l2 == null) ? 0 : l2.val) + ((l1 == null) ? 0 : l1.val) + carry;
            node.val = sum % 10;
            carry = sum / 10;
            cur.next = node;
            cur = node;
            l1 = (l1 == null) ? l1 : l1.next;
            l2 = (l2 == null) ? l2 : l2.next;
        }

        return res.next;
    }
}
