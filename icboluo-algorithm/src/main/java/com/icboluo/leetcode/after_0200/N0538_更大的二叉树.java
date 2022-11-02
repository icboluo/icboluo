package com.icboluo.leetcode.after_0200;

import com.icboluo.common.TreeNode;

/**
 * @author icboluo
 * @since 2022-03-29 21:36
 */
 class N0538_更大的二叉树 {
    int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        if (root == null) {
            return null;
        }
        convertBST(root.right);
        root.val += sum;
        sum = root.val;
        convertBST(root.left);
        return root;
    }
}
