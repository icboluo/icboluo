package com.icboluo.leetcode.after_0300;

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
        return Math.max(left, right) + 1;
    }
}
