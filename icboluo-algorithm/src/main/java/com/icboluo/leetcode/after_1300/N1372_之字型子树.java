package com.icboluo.leetcode.after_1300;

import com.icboluo.common.TreeNode;

/**
 * @author icboluo
 * @since 2022-05-29 16:56
 */
class N1372_之字型子树 {
    int max = 0;

    public int longestZigZag(TreeNode root) {
        if (root == null) {
            return 0;
        }
        path(root.right, 0, true);
        path(root.left, 0, false);
        return max;
    }

    private void path(TreeNode root, int depth, boolean isRight) {
        max = Math.max(max, depth);
        if (root == null) {
            return;
        }
        path(root.left, isRight ? depth + 1 : 0, false);
        path(root.right, isRight ? 0 : depth + 1, true);
    }
}
