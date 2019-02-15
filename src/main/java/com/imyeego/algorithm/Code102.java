package com.imyeego.algorithm;

import java.util.*;

/**
 * @authur : liuzhao
 * @time : 2019/2/12
 * @email : imyeego@gmail.com
 * @description : Binary Tree Level Order Traversal
 */
public class Code102 {

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

        List<List<Integer>> list = zigzagLevelOrder(root);
        System.out.println(list);
    }

    /**
     * 层次遍历，用队列实现，from LeetCode#102 & Offer60
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            List<Integer> level = new ArrayList<>();
            for (int i = queue.size(); i > 0; --i){
                TreeNode temp = queue.poll();
                level.add(temp.val);
                if (temp.left != null) queue.add(temp.left);
                if (temp.right != null) queue.add(temp.right);
            }
            res.add(level);

        }
        return res;
    }

    /**
     * “之”字形打印二叉树，from Offer#61 & LeetCode#103
     * @param root
     * @return
     */
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        boolean flag = true;
        Deque<TreeNode> deque = new LinkedList<>();
        Deque<TreeNode> childDeque = new LinkedList<>();
        deque.add(root);

        while (!deque.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            for (int i = deque.size(); i > 0 ; --i) {
                TreeNode temp;
                if (flag) {
                    temp = deque.poll();
                    if (temp.left != null) childDeque.offer(temp.left);
                    if (temp.right != null) childDeque.offer(temp.right);
                } else {
                    temp = deque.pollLast();
                    if (temp.right != null) childDeque.push(temp.right);
                    if (temp.left != null) childDeque.push(temp.left);
                }
                level.add(temp.val);

            }
            deque.addAll(childDeque);
            childDeque.clear();
            flag = !flag;
            res.add(level);
        }

        return res;
    }
}
