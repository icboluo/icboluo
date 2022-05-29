package com.icboluo.leetcode.onethousand;

import com.icboluo.common.TreeNode;

/**
 * @author icboluo
 * @since 2022-04-05 22:22
 */
public class N0687_最小单值路径 {
    public int longestUnivaluePath(TreeNode root) {
//        m(root, 0);
        getLength(root);
        return max;
    }

    int max = 0;

    public int m(TreeNode root, int pre) {
        if (root == null) {
            return 0;
        }
        int left = m(root.left, root.val);
        int right = m(root.right, root.val);
        max = Math.max(max, left + right);
        if (pre == root.val) {
            return Math.max(left, right) + 1;
        }
        return 0;
    }

    private int getLength(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getLength(root.left);
        int right = getLength(root.right);
        int leftRes = 0;
        if (root.left != null && root.left.val == root.val) {
            leftRes = left + 1;
        }
        int rightRes = 0;
        if (root.right != null && root.right.val == root.val) {
            rightRes = right + 1;
        }
        max = Math.max(max, leftRes + rightRes);
        return Math.max(leftRes, rightRes);
    }
}
