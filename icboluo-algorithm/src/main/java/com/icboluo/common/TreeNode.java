package com.icboluo.common;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

/**
 * 二叉树树节点，并不对树进行方法抽取，只做算法使用
 *
 * @author icboluo
 * @since 2020-09-27 19:12
 */

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    /**
     * 完全二叉树的构造方法
     *
     * @param arr 层级遍历的数组
     */
    public TreeNode(Integer[] arr) {
        TreeNode treeNode = getInstance(arr, 0);
        if (treeNode == null) {
            return;
        }
        this.val = treeNode.val;
        this.left = treeNode.left;
        this.right = treeNode.right;
    }

    /**
     * TODO it is error
     *
     * @param arr 中序遍历数组
     * @return 树的根节点
     */
    public static TreeNode getLevelOrderInstance(Integer[] arr) {
        if (arr == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(arr[0]);
        queue.offer(root);
        int index = 1;
        while (index < arr.length - 1) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (poll == null) {
                    continue;
                }
//                如果节点是值为null，代表left or right 为null，不需要构造空子树
                if (arr[index] != null) {
                    poll.left = new TreeNode(arr[index]);
                    queue.offer(poll.left);
                }
                if (arr[index + 1] != null) {
                    poll.right = new TreeNode(arr[index + 1]);
                    queue.offer(poll.right);
                }
                index += 2;

            }
        }
        return root;
    }

    /**
     * 收集节点val
     *
     * @param list 节点集合
     * @return val集合
     */
    public static List<Integer> collectNodeVal(List<TreeNode> list) {
        return list.stream().map(treeNode -> treeNode.val).toList();
    }

    private TreeNode getInstance(Integer[] arr, int index) {
        if (index >= arr.length || arr[index] == null) {
            return null;
        }
        TreeNode cur = new TreeNode(arr[index]);
        cur.left = getInstance(arr, index * 2 + 1);
        cur.right = getInstance(arr, index * 2 + 2);
        return cur;
    }

    public static void printTree(TreeNode head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "-", 17);
        System.out.println();
    }

    private static void printInOrder(TreeNode head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "^", len);
        String val = to + head.val + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "v", len);
    }

    private static String getSpace(int num) {
        String space = " ";
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TreeNode treeNode = (TreeNode) o;
        return val == treeNode.val;
    }

    @Override
    public int hashCode() {
        return Objects.hash(val);
    }
}
