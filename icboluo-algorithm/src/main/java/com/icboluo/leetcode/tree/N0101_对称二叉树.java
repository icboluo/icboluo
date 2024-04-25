package com.icboluo.leetcode.tree;

import com.icboluo.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author icboluo
 * @since 2020-09-29 20:20
 */
class N0101_对称二叉树 {
    public static void main(String[] args) {
        var cla = new N0101_对称二叉树();
        TreeNode treeNode = new TreeNode(1, 2, 2, 3, 4, 4, 3);
        System.out.println(cla.isSymmetric1(treeNode));
        System.out.println(cla.isSymmetric2(treeNode));
    }

    /**
     * 一棵树是对称的等价于它的左子树和右子树两棵树是对称
     * 用递归来处理
     */
    public boolean isSymmetric1(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSame(root.left, root.right);
    }

    private boolean isSame(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        return isSame(left.left, right.right) && isSame(left.right, right.left);
    }

    public boolean isSymmetric2(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode first = queue.poll();
            TreeNode second = queue.poll();
            if (first == null && second == null) {
                continue;
            }
            if (first == null || second == null) {
                return false;
            }
            if (first.val != second.val) {
                return false;
            }
            queue.add(first.left);
            queue.add(second.right);
            queue.add(first.right);
            queue.add(second.left);
        }
        return true;
    }
}
