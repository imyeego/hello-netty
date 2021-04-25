package com.imyeego.algorithm;

/**
 * @authur : liuzhao
 * @date : 4/2/21:6:34 PM
 * @des :
 */
public class Offer57 {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;

        ListNode res = removeDup1(node1);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }

    /**
     * 删除排序链表重复节点
     * input: 1->2->2->3->3->4->5
     * output: 1->4->5
     * @param root
     * @return
     */
    static ListNode removeDup(ListNode root) {
        ListNode first = new ListNode(-1);
        first.next = root;
        ListNode prev = first, cur = root;

        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                int val = cur.val;
                while (cur != null && cur.val == val) cur = cur.next;
                prev.next = cur;
            } else {
                prev = cur;
                cur = cur.next;
            }
        }


        return first.next;
    }

    /**
     * 删除排序链表重复节点
     * input: 1->2->2->3->3->4->5
     * output: 1->2->3->4->5
     * @param root
     * @return
     */
    static ListNode removeDup1(ListNode root) {
        if (root == null) return null;
        ListNode prev = root, cur = root.next;

        while (cur != null) {
            if (prev.val == cur.val) {
                int val = cur.val;
                while (cur != null && cur.val == val) cur = cur.next;
                prev.next = cur;
            } else {
                prev = cur;
                cur = cur.next;
            }
        }


        return root;
    }
}
