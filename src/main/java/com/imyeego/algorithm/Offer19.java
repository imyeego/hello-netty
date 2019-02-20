package com.imyeego.algorithm;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @authur : liuzhao
 * @time : 2019/2/18
 * @email : imyeego@gmail.com
 * @description : invert the binary tree
 */
public class Offer19 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(7);
        TreeNode node3 = new TreeNode(1);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(6);
        TreeNode node6 = new TreeNode(9);

        root.right = node2;
        root.left = node1;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;

        root = invertTreeIterative(root);
        List<List<Integer>> list = Code102.levelOrder(root);
        System.out.println(list);
    }


    /**
     * 递归法
     * from LeetCode#226
     * @param root
     * @return
     */
    public static TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode node = invertTree(root.left);
        root.left = invertTree(root.right);
        root.right = node;
        return root;
    }

    /**
     * 迭代法
     * from LeetCode#226
     * @param root
     * @return
     */
    public static TreeNode invertTreeIterative(TreeNode root) {
        if (root == null) return null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            for (int i = 0; i < queue.size(); i++) {
                TreeNode temp = queue.poll();
                TreeNode t = temp.left;
                temp.left = temp.right;
                temp.right = t;
                if (temp.left != null) queue.add(temp.left);
                if (temp.right != null) queue.add(temp.right);
            }
        }

        return root;
    }
}
