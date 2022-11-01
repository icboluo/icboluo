package com.icboluo.leetcode.after_1000;

import com.icboluo.common.TreeNode;

/**
 * @author icboluo
 * @since 2022-07-10 13:23
 */
class N1448_二叉树中的好节点 {
    public int goodNodes(TreeNode root) {
        return dfs(root, -99999);
    }

    private int dfs(TreeNode root, int preMax) {
        if (root == null) {
            return 0;
        }
        int ans = root.val >= preMax ? 1 : 0;
        preMax = Math.max(preMax, root.val);
        int left = dfs(root.left, preMax);
        int right = dfs(root.right, preMax);
        return ans + left + right;
    }
}
