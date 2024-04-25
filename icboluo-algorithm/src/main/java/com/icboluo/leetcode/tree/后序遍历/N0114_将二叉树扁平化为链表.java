package com.icboluo.leetcode.tree.后序遍历;

import com.icboluo.common.TreeNode;

/**
 * @author icboluo
 * @since 2024-04-25 21:43
 */
class N0114_将二叉树扁平化为链表 {
    private TreeNode prev;

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        flatten(root.right);
        flatten(root.left);
        root.left = null;
        root.right = prev;
        prev = root;
    }
}
