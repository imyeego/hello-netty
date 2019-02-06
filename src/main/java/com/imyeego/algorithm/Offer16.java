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

        ListNode node = reverseList(node1);
        while (node != null){
            System.out.println(node.val);
            node = node.next;
        }
    }


    private static ListNode reverseList(ListNode head){
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
    static class ListNode{
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }
}
