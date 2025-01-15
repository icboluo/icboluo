package com.icboluo.leetcode.tree;

import com.icboluo.common.TreeNode;

/**
 * @author icboluo
 * @since 2022-03-29 21:42
 */
 class N0543_二叉树直径 {
    int ans = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        diameterTree(root);
        return ans;
    }

    public int diameterTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = diameterTree(root.left);
        int right = diameterTree(root.right);
        ans = Math.max(ans, left + right);
        // 返回的结果是当前节点的高度，ans在前面计算，所以ans计算的结果中不含当前节点
        return Math.max(left, right) + 1;
    }
}
