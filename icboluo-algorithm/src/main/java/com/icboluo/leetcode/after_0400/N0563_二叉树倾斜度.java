package com.icboluo.leetcode.after_0400;

import com.icboluo.common.TreeNode;

/**
 * @author icboluo
 * @since 2022-03-29 21:52
 */
 class N0563_二叉树倾斜度 {
    int sum = 0;

    public int findTilt(TreeNode root) {
        find(root);
        return sum;
    }

    /**
     * 求子树和
     *
     * @param root
     * @return
     */
    public int find(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = find(root.left);
        int right = find(root.right);
        sum += Math.abs(left - right);
        return left + right + root.val;
    }
}
