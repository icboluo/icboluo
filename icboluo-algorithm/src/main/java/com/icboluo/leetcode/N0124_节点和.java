package com.icboluo.leetcode;

import com.icboluo.common.TreeNode;

/**
 * @author icboluo
 * @date 2022-03-25 19:04
 */
public class N0124_节点和 {

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
//        why error
        return cur;
    }
}
