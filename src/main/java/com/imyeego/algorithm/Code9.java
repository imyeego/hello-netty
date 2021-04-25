package com.imyeego.algorithm;

/**
 * @authur : liuzhao
 * @time : 2019/3/20
 * @email : imyeego@gmail.com
 * @description : Palindrome Number
 */
public class Code9 {

    public static void main(String[] args) {
        ListNode root = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(1);
//        ListNode node5 = new ListNode(2);

        root.next = node1;
        node1.next = node2;
//        node2.next = node3;
//        node3.next = node4;
//        node4.next = node5;
//        while (node != null) {
//            System.out.println(node.val);
//            node = node.next;
//        }
//        System.out.println(isPalindrome(90));
        System.out.println(isPalindrome(root));
    }

    /**
     * LeetCode#9
     * @param x
     * @return
     */
    public static boolean isPalindrome(int x) {
        if (x < 0) return false;
        int res = 0, temp = x;
        while (x != 0) {
            res = res * 10 + x % 10;
            x /= 10;
        }

        return res == temp;
    }

    /**
     * LeetCode#234
     * @param root
     *
     * 1->2->3->4->5->6
     *
     *
     * @return
     */
    public static boolean isPalindrome(ListNode root) {
        if (root == null) return true;
        ListNode left = root, right = root, temp = root;
        while (right != null && right.next != null) {
            if (right.next.next == null) temp = left; // 当节点数为偶数时
            left = left.next;
            right = right.next.next;
            if (right == null) temp.next = null; // 当节点数为偶数时
        }
        ListNode re = Offer16.reverseByIterator(left);
        while (root != null && re != null) {
            if (root.val != re.val) return false;
            root = root.next;
            re = re.next;
        }
        return true;
    }
}
