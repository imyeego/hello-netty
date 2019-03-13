package com.imyeego.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @authur : liuzhao
 * @time : 2019/2/26
 * @email : imyeego@gmail.com
 * @description :
 */
public class Offer25 {
    private static List<List<Integer>> list = new ArrayList<>();

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(7);
        TreeNode node3 = new TreeNode(11);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(6);
        TreeNode node6 = new TreeNode(9);
        TreeNode node7 = new TreeNode(8);

        root.right = node2;
        root.left = node1;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;
        node4.left = node7;

        List<List<Integer>> res = findPath(root, 17);
        res.forEach(System.out :: println);
    }

    private static List<List<Integer>> findPath(TreeNode root, final int expectNum) {
        if (root == null) return list;
        List<Integer> path = new ArrayList<>();
        findPathRecursive(root, expectNum, path, 0);
        return list;
    }

    /**
     * 当java 为对象引用时，void函数会改变对象的值，所以需要再递归结束时弹出栈顶用以保存路径
     * @param node
     * @param expectNum
     * @param path
     * @param currentNum
     */
    private static void findPathRecursive(TreeNode node, int expectNum, List<Integer> path, int currentNum) {
        currentNum += node.val;
        path.add(node.val);
        if (currentNum == expectNum && (node.left == null && node.right == null)) {
            list.add(new ArrayList<>(path));
        }

        if (node.left != null) findPathRecursive(node.left, expectNum, path, currentNum);
        if (node.right != null) findPathRecursive(node.right, expectNum, path, currentNum);
        path.remove(path.size() - 1);
    }
}
