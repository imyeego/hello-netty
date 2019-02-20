package com.imyeego.algorithm;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Authur: liuzhao
 * @Date: 2019/2/6 0006 17:32
 * @Email: imyeego@gmail.com
 * @Description: merge two sorted linked-list
 */
public class Offer17 {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(4);
        ListNode node1_ = new ListNode(-3);
        ListNode node2_ = new ListNode(13);
        ListNode node3_ = new ListNode(15);
        ListNode node4_ = new ListNode(16);
        ListNode node1__ = new ListNode(3);
        ListNode node2__ = new ListNode(6);
        ListNode node3__ = new ListNode(9);

        node1.next = node2;
        node2.next = node3;
        node3.next = null;
        node1_.next = node2_;
        node2_.next = node3_;
        node3_.next = node4_;
        node4_.next = null;
        node1__.next = node2__;
        node2__.next = node3__;
        node3__.next = null;

        ListNode[] lists = new ListNode[]{node1, node1_, node1__};
        ListNode head = mergeKLists(lists);

        while (head != null){
            System.out.print(head.val + "->");
            head = head.next;
        }
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null){
            return l2;
        }
        if (l2 == null){
            return l1;
        }
        ListNode p, p1 = l1, p2 = l2;
        if (p1.val <= p2.val){
            p = p1;
            p1 = p1.next;
        }else{
            p = p2;
            p2 = p2.next;
        }

        ListNode cur = p;
        while (p1 != null && p2 != null) {
            if (p1.val <= p2.val) {
                cur.next = p1;
                p1 = p1.next;
            }else {
                cur.next = p2;
                p2 = p2.next;
            }
            cur = cur.next;
        }

        if (p1 != null){
            cur.next = p1;
        }else {
            cur.next = p2;
        }

        return p;
    }

    /**
     * from LeetCode#23
     * @param lists
     * @return
     */
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        ListNode p = new ListNode(0);
        ListNode cur = p;
        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, (o1, o2) -> {
            if (o1.val < o2.val) return -1;
            else if (o1.val == o2.val) return 0;
            else return 1;
        });
        for (ListNode node : lists) {
            if (node != null) queue.add(node);
        }

        while (!queue.isEmpty()) {
            cur.next = queue.poll();
            cur = cur.next;
            if (cur.next != null) queue.add(cur.next);
        }
        return p.next;
    }


    static class ListNode{
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }
}
