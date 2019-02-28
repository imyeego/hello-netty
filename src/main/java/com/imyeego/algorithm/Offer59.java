package com.imyeego.algorithm;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @authur : liuzhao
 * @time : 2019/2/27
 * @email : imyeego@gmail.com
 * @description : Symmetric Tree
 */
public class Offer59 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(3);

        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;
        System.out.println(isSymmetricIteratively(root));
    }

    public static boolean isSymmetrical(TreeNode root) {
        if (root == null) return true;
        return isSymmetrical(root.left, root.right);
    }

    public static boolean isSymmetrical(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        if (left.val != right.val) return false;
        return isSymmetrical(left.left, right.right) && isSymmetrical(left.right, right.left);
    }

    public static boolean isSymmetricIteratively(TreeNode root) {
        if (root == null) return true;
        if (root.left == null && root.right == null) return true;

        if ((root.left == null && root.right != null) ||
                root.right == null && root.left != null) return false;

        Deque<TreeNode> deque = new LinkedList<>();
        deque.push(root.left);
        deque.add(root.right);

        while (!deque.isEmpty()) {
            TreeNode leftRoot = deque.poll();
            TreeNode rightRoot = deque.pollLast();

            if (leftRoot.val != rightRoot.val) return false;

            if ((leftRoot.right == null && rightRoot.left != null) ||
                    leftRoot.right != null && rightRoot.left == null) return false;

            if (leftRoot.right != null) {
                deque.push(leftRoot.right);
                deque.add(rightRoot.left);
            }

            if ((leftRoot.left == null && rightRoot.right != null) ||
                    leftRoot.left != null && rightRoot.right == null) return false;

            if (leftRoot.left != null) {
                deque.push(leftRoot.left);
                deque.add(rightRoot.right);
            }
        }
        return true;
    }
}
