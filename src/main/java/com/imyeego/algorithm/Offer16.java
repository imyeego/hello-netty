package com.imyeego.algorithm;

/**
 * @Authur: liuzhao
 * @Date: 2019/2/5 0005 23:16
 * @Email: imyeego@gmail.com
 * @Description: reverse linked-list
 */
public class Offer16 {


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

//        ListNode node = reverseList(node1);
        ListNode node = reverseBetween(node1, 3, 6);
        while (node != null){
            System.out.println(node.val);
            node = node.next;
        }
    }


    public static ListNode reverseList(ListNode head){
        if (head == null) {
            return null;
        }
        ListNode pNode = head, nNode, pNext = null;

        nNode = pNode;
        pNode = pNode.next;
        nNode.next = null;

        while (pNode != null){
            pNext = pNode.next; // save the next node, avoid missing

            pNode.next = nNode;
            nNode = pNode;
            pNode = pNext; // move the pointer
        }

        return nNode;
    }

    private static ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null) {
            return null;
        }
        if (m < 1 || m > n) {
            return null;
        }
        ListNode pNode = head, mNode = null, sNode = null, eNode = null, nNode = null, pNext = null;
        int i = 1;
        while (pNode != null) {
            pNext = pNode.next;
            if (m != 1) {
                if (i == m - 1) {
                    mNode = pNode;
                }
                if (i == n) mNode.next = pNode;
            }
            if (m == 1 && i == n) eNode = pNode;
            if (i > m && i <= n) {
                pNode.next = nNode;
            }

            if (i == m) {
                sNode = pNode;
            }

            if (i == n + 1) sNode.next = pNode;
            nNode = pNode;
            pNode = pNext;
            i ++;
        }

        if (i - n == 1) sNode.next = null;
        if (i - n < 1) return null;
        if (m == 1) {
            return eNode;
        }
        return head;
    }

//    static class ListNode{
//        int val;
//        ListNode next;
//
//        public ListNode(int val) {
//            this.val = val;
//        }
//    }
}
