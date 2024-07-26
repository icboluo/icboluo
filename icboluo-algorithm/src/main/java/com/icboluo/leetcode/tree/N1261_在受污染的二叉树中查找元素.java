package com.icboluo.leetcode.tree;

import com.icboluo.common.TreeNode;

/**
 * @author icboluo
 * @since 2024-07-26 上午8:34
 */
class N1261_在受污染的二叉树中查找元素 {
    TreeNode head;

    public N1261_在受污染的二叉树中查找元素(TreeNode root) {
        head = new TreeNode(0);
        dfs(root, head);
    }

    private void dfs(TreeNode root, TreeNode newRoot) {
        if (root == null) {
            return;
        }
        // 标准解法，但是并非最优，最优解法应该以set集合做容器
        if (root.left != null) {
            newRoot.left = new TreeNode(2 * newRoot.val + 1);
            dfs(root.left, newRoot.left);
        }
        if (root.right != null) {
            newRoot.right = new TreeNode(2 * newRoot.val + 2);
            dfs(root.right, newRoot.right);
        }
    }

    public boolean find(int target) {
        return dfs(head, target);
    }

    public boolean dfs(TreeNode root, int target) {
        if (root == null) {
            return false;
        }
        return root.val == target || dfs(root.left, target) || dfs(root.right, target);
    }

    public static void main(String[] args) {
        var cla = new N1261_在受污染的二叉树中查找元素(new TreeNode(-1, -1, -1, -1, -1));
        System.out.println(cla.find(1));
        System.out.println(cla.find(3));
        System.out.println(cla.find(5));
    }
}
