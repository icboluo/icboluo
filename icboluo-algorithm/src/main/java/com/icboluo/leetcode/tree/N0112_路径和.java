package com.icboluo.leetcode.tree;

import com.icboluo.common.TreeNode;

/**
 * @author icboluo
 * @since 2025-03-19 23:08
 */
class N0112_路径和 {
    public static void main(String[] args) {
        var cla = new N0112_路径和();
        TreeNode root = new TreeNode(5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1);
        System.out.println(cla.hasPathSum(root, 12));
    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null && root.val == targetSum) {
            return true;
        }
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }
}
