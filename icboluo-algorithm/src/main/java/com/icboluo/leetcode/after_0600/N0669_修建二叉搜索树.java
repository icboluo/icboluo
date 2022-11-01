package com.icboluo.leetcode.after_0600;

import com.icboluo.common.TreeNode;

/**
 * @author icboluo
 * @since 2021-11-19 13:11
 */
 class N0669_修建二叉搜索树 {
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }
        if (root.val > high) {
            return trimBST(root.left, low, high);
        }
        if (root.val < low) {
            return trimBST(root.right, low, high);
        }
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        return root;
    }
}
