package com.icboluo.leetcode.fivehundred;

import com.icboluo.common.TreeNode;

/**
 * @author icboluo
 * @since 2022-03-25 19:09
 */
public class N0129_节点和 {
    public int sumNumbers(TreeNode root) {
        return sumNumbers(root, 0);
    }

    private int sumNumbers(TreeNode root, int before) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return before * 10 + root.val;
        }

        int left = sumNumbers(root.left, before * 10 + root.val);
        int right = sumNumbers(root.right, before * 10 + root.val);
        return left + right;
    }
}
