package com.icboluo.leetcode;

import com.icboluo.common.TreeNode;

/**
 * @author icboluo
 * @date 2021-59-28 12:59
 */
public class N0979 {
    int ans;

    public int distributeCoins(TreeNode root) {
        ans = 0;
        int dfs = dfs(root);
        return ans;
    }

    /**
     * @param root 根节点
     * @return 当前节点剩余硬币的数量
     */
    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = dfs(root.left);
        int right = dfs(root.right);
        ans += Math.abs(left) + Math.abs(right);
        return root.val + left + right - 1;
    }
}
