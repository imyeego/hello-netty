package com.imyeego.algorithm;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @authur : liuzhao
 * @time : 2019/2/12
 * @email : imyeego@gmail.com
 * @description : max and min depth of binary tree
 */
public class Offer39 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(20);
        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(7);

        root.left = node1;
        root.right = node2;
        node2.left = node3;
        node2.right = node4;

        System.out.println(minDepthIterative(root));
    }
    public static int maxDepth(TreeNode root) {
        if (root == null)
            return 0;

        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    public static int minDepth(TreeNode root) {
        if (root == null)
            return 0;
        if (root.left == null) return minDepth(root.right) + 1;
        if (root.right == null) return minDepth(root.left) + 1;
        int leftDepth = minDepth(root.left);
        int rightDepth = minDepth(root.right);
        return Math.min(leftDepth, rightDepth) + 1;
    }

    public static int maxDepthIterative(TreeNode root){
        int count = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            count ++;
            for (int i = queue.size() - 1; i >= 0 ; i--) {
                TreeNode temp = queue.poll();
                if (temp.left != null) queue.add(temp.left);
                if (temp.right != null) queue.add(temp.right);
            }
        }
        return count;
    }

    public static int minDepthIterative(TreeNode root){
        int count = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            count ++;
            for (int i = queue.size() - 1; i >= 0 ; i--) {
                TreeNode temp = queue.poll();
                if (temp.left == null && temp.right == null) return count;
                if (temp.left != null) queue.add(temp.left);
                if (temp.right != null) queue.add(temp.right);
            }
        }
        return -1;
    }
}
