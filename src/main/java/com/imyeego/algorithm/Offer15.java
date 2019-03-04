package com.imyeego.algorithm;

/**
 * @authur : liuzhao
 * @time : 2019/3/4
 * @email : imyeego@gmail.com
 * @description : the k-th to tail
 */
public class Offer15 {

    public static void main(String[] args) {
        ListNode root = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);
        ListNode node4 = new ListNode(5);
        ListNode node5 = new ListNode(6);

        root.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        ListNode node = findKthToTail(root, 6);
        System.out.println(node.val);
    }

    private static ListNode findKthToTail(ListNode root, int k) {
        if (root == null) return null;
        ListNode left = root, right = root;

        for (int i = 0; i < k && right != null; i++) {
            right = right.next;
        }

        if (right == null) return null;
        while (right != null) {
            left = left.next;
            right = right.next;
        }
        return left;
    }
}
