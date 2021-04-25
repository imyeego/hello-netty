package com.imyeego.algorithm;

import java.util.Stack;

/**
 * @authur : liuzhao
 * @date : 4/1/21:1:59 PM
 * @des :
 */
public class Offer63 {

    static int count = 0;
    public static void main(String[] args) {
        TreeNode node5 = new TreeNode(5);
        TreeNode node3 = new TreeNode(3);
        TreeNode node7 = new TreeNode(7);
        node5.left = node3;
        node5.right = node7;
        TreeNode node2 = new TreeNode(2);
        TreeNode node4 = new TreeNode(4);
        node3.left = node2;
        node3.right = node4;

        TreeNode node6 = new TreeNode(6);
        TreeNode node8 = new TreeNode(8);
        node7.left = node6;
        node7.right = node8;


//        TreeNode res = findKth(node5, 3);
//        TreeNode res = findTopK(node5, 4);
//        TreeNode res = findKthByIt(node5, 1);
        TreeNode res = findTopKByIt(node5, 2);
        if (res != null) {
            System.out.println(res.val);
        }
    }


    static TreeNode findKth(TreeNode root, int k) {
        if (root == null) {
            return null;
        }

        TreeNode res;

        if ((res = findKth(root.left, k)) != null) return res;
        count ++;
        if (count == k) return root;
        if ((res = findKth(root.right, k)) != null) return res;

        return null;
    }

    static TreeNode findKthByIt(TreeNode root, int k) {
        if (root == null) return null;
        int count = 0;
        Stack<TreeNode> stack = new Stack<>();

        while (true) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                if (stack.isEmpty()) return null;
                root = stack.pop();
                count ++;
                if (count == k) return root;
                else root = root.right;
            }
        }
    }

    static TreeNode findTopK(TreeNode root, int k) {
        if (root == null) {
            return null;
        }

        TreeNode res;

        if ((res = findTopK(root.right, k)) != null) return res;
        count ++;
        if (count == k) return root;
        if ((res = findTopK(root.left, k)) != null) return res;

        return null;
    }


    static TreeNode findTopKByIt(TreeNode root, int k) {
        if (root == null) return null;
        int count = 0;
        Stack<TreeNode> stack = new Stack<>();

        while (true) {
            if (root != null) {
                stack.push(root);
                root = root.right;
            } else {
                if (stack.isEmpty()) return null;
                root = stack.pop();
                count ++;
                if (count == k) return root;
                else root = root.left;
            }
        }
    }
}
