package com.icboluo.leetcode.tree;

import com.icboluo.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author icboluo
 * @since 2021-39-10 12:39
 */
class N0700_二叉搜索树中搜索 {
    public static void main(String[] args) {
        var cla = new N0700_二叉搜索树中搜索();
        TreeNode root = new TreeNode(4, 2, 7, 1, 3);
        root.print();
        TreeNode treeNode = cla.searchBST(root, 2);
        treeNode.print();
    }

    public TreeNode searchBST(TreeNode root, int val) {
        return searchBST1(root, val);
    }

    //  强解
    public TreeNode searchBST1(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (poll.val == val) {
                    return poll;
                }
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
            }
        }
        return null;
    }

    //  强解递归方法省略
    public TreeNode searchBST2(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (root.val == val) {
            return root;
        }
        if (root.val > val) {
            return searchBST2(root.left, val);
        } else {
            return searchBST2(root.right, val);
        }
    }

    // 利用bst的特性
    public TreeNode searchBST3(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        while (root != null && root.val != val) {
            root = root.val > val ? root.left : root.right;
        }
        return root;
    }
}
