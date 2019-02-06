package com.imyeego.algorithm;

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

        node1.next = node2;
        node2.next = node3;
        node3.next = null;

        node1_.next = node2_;
        node2_.next = node3_;
        node3_.next = null;

        ListNode head = mergeTwoLists(node1, node1_);
        while (head != null){
            System.out.println(head.val);
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

    public static ListNode mergeKLists(ListNode[] lists) {
        return null;
    }


    static class ListNode{
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }
}
