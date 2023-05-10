package com.icboluo.leetcode.after_0000;

import com.icboluo.common.TreeNode;

/**
 * @author icboluo
 * @since 2022-03-25 19:04
 */
class N0124_二叉树中的最大路径和 {

    int ans;

    public int maxPathSum(TreeNode root) {
        ans = Integer.MIN_VALUE;
        maxSum(root);
        return ans;
    }

    private int maxSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
//        存在为负数不计入路径的情况
        int left = Math.max(0, maxSum(root.left));
        int right = Math.max(0, maxSum(root.right));
        int cur = left + right + root.val;
        ans = Math.max(ans, cur);
//       这里求的是当前节点一下的最大值，是单路径
        return Math.max(left, right) + root.val;
    }
}
