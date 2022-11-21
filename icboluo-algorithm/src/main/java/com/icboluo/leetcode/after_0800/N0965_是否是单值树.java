package com.icboluo.leetcode.after_0800;

import com.icboluo.common.TreeNode;

class N0965_是否是单值树 {
    public boolean isUnivalTree(TreeNode root) {
        return isUnivalTree1(root, root);
    }

    private boolean isUnivalTree1(TreeNode root, TreeNode cur) {
        if (cur == null) {
            return true;
        }
        if (cur.val != root.val) {
            return false;
        }
        return isUnivalTree1(root, cur.left) && isUnivalTree1(root, cur.right);
    }

    private boolean isUnivalTree2(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean left = root.left == null || (root.left.val == root.val && isUnivalTree2(root.left));
        boolean right = root.right == null || (root.right.val == root.val && isUnivalTree2(root.right));
        return left && right;
    }
}
