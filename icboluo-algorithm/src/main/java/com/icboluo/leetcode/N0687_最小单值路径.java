package com.icboluo.leetcode;

import com.icboluo.common.TreeNode;

/**
 * @author icboluo
 * @since 2022-04-05 22:22
 */
public class N0687_最小单值路径 {
    public int longestUnivaluePath(TreeNode root) {
        m(root, 0);
        return max;
    }

    int max = 0;

    public int m(TreeNode root, int pre) {
        if (root == null) {
            return 0;
        }
        int left = m(root.left, root.val);
        int right = m(root.right, root.val);
        max = Math.max(max, left + right);
        if (pre == root.val) {
            return Math.max(left, right) + 1;
        }
        return 0;
    }
}
