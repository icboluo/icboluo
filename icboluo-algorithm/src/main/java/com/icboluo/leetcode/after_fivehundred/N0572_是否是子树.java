package com.icboluo.leetcode.after_fivehundred;

import com.icboluo.common.TreeNode;

/**
 * @author icboluo
 * @since 2022-03-29 22:02
 */
 class N0572_是否是子树 {
    boolean ans;

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        find(root, subRoot);
        return ans;
    }

    private boolean equal(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null) {
            return true;
        }
        if (root == null || subRoot == null) {
            return false;
        }
        if (root.val != subRoot.val) {
            return false;
        }
        return equal(root.left, subRoot.left) && equal(root.right, subRoot.right);
    }

    private void find(TreeNode root, TreeNode subRoot) {
//        提早结束
        if (root == null || ans) {
            return;
        }
        if (root.val == subRoot.val) {
            ans = equal(root, subRoot);
        }
        find(root.left, subRoot);
        find(root.right, subRoot);
    }
}
