package com.icboluo.leetcode.onethousand;

import com.icboluo.common.TreeNode;

/**
 * @author icboluo
 * @since 2022-05-29 17:11
 */
public class N1139_二叉树子树最大乘积 {
    int total = 0;
    int max = 0;

    public int maxProduct(TreeNode root) {
        total = total(root);
        maxFun(root);
        return max;
    }

    private int maxFun(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxFun(root.left);
        int right = maxFun(root.right);
        int sub = root.val + left + right;
        max = Math.max(max, sub * (total - sub));
        return sub;
    }

    private int total(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = total(root.left);
        int right = total(root.right);
        return root.val + left + right;
    }
}
