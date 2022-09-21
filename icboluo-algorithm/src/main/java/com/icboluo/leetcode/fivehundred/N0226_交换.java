package com.icboluo.leetcode.fivehundred;

import com.icboluo.common.TreeNode;

/**
 * @author icboluo
 * @since 2022-03-25 19:42
 */
class N0226_交换 {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = invertTree(root.left);
        root.left = invertTree(root.right);
        root.right = left;
        return root;
    }
}
