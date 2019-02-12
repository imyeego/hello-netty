package com.imyeego.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

        List<List<Integer>> list = levelOrder(root);
        System.out.println(list);
    }

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
}
