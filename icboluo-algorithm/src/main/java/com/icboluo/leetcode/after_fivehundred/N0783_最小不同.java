package com.icboluo.leetcode.after_fivehundred;

import com.icboluo.common.TreeNode;

/**
 * @author icboluo
 * @since 2022-09-20 0:31
 */
public class N0783_最小不同 {
    int min = Integer.MAX_VALUE;

    public int minDiffInBST(TreeNode root) {
        return dfs(root, root);
    }

    private int dfs(TreeNode pre, TreeNode root) {
        if (root == null) {
            return Integer.MAX_VALUE;
        }
        // 保留前驱节点即可，递归过程中OPS ans；方法返回最小的left or right
        int min = Math.abs(pre.val - root.val);
        this.min = Math.min(min, this.min);
        int left = dfs(root, root.left);
        int right = dfs(root, root.right);
        return Math.min(left, right);
    }
}
