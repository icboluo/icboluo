package com.icboluo.leetcode.tree;

import com.icboluo.common.TreeNode;

/**
 * @author icboluo
 * @since 2021-59-28 12:59
 */
class N0979_在二叉树中分配硬币 {
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
        // 后序遍历，挺难理解的，因为中途收集了结果
        ans += Math.abs(left) + Math.abs(right);
        return root.val + left + right - 1;
    }
}
