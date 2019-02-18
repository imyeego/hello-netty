package com.imyeego.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @authur : liuzhao
 * @time : 2019/2/18
 * @email : imyeego@gmail.com
 * @description : Binary Tree Postorder Traversal
 */
public class Code145 {

    private static  List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(5);
        TreeNode node4 = new TreeNode(4);

        root.right = node1;
        root.left = node4;
        node1.left = node2;
        node1.right = node3;

        List<Integer> list = inorderTraversalIterative(root);
        for (Integer i : list) {
            System.out.println(i);
        }
    }

    public static List<Integer> postorderTraversalRecursive(TreeNode root) {
        if (root == null) return list;
        if (root.left != null) postorderTraversalRecursive(root.left);
        if (root.right != null) postorderTraversalRecursive(root.right);

        list.add(root.val);
        return list;
    }

    /**
     * LeetCode#145
     * @param root
     * @return
     */
    public static List<Integer> postorderTraversalIterative(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        if (root == null) return list;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode temp = stack.pop();
            list.add(0, temp.val);
            if (temp.left != null) stack.push(temp.left);
            if (temp.right != null) stack.push(temp.right);
        }
        return list;
    }

    /**
     * LeetCode#144
     * @param root
     * @return
     */
    public static List<Integer> preorderTraversalIterative(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        if (root == null) return list;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode temp = stack.pop();
            list.add(temp.val);
            if (temp.right != null) stack.push(temp.right);
            if (temp.left != null) stack.push(temp.left);
        }
        return list;
    }

    /**
     * LeetCode#94
     * @param root
     * @return
     */
    public static List<Integer> inorderTraversalIterative(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (true) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                if (stack.empty()) return list;
                root = stack.pop();
                list.add(root.val);
                root = root.right;
            }
        }
    }
}
