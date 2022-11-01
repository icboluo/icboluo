package com.icboluo.leetcode.after_1000;

import com.icboluo.common.TreeNode;

/**
 * @author icboluo
 * @since 2022-07-10 21:13
 */
class N1038_二叉搜索树和 {
    int pre = 0;

    public TreeNode bstToGst(TreeNode root) {
        if (root == null) {
            return root;
        }
        bstToGst(root.right);
        root.val += pre;
        pre = root.val;
        bstToGst(root.left);
        return root;
    }
}
