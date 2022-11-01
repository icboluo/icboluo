package com.icboluo.leetcode.after_1000;

import com.icboluo.common.TreeNode;

/**
 * @author icboluo
 * @since 2021-05-04 13:05
 */
class N1026 {
    public int maxAncestorDiff(TreeNode root) {
        return maxAncestorDiff1(root);
//        return maxAncestorDiff2(root);
    }

    //  结果实时计算，动态规划思维
    public int maxAncestorDiff1(TreeNode root) {
        return dfs1(root, root.val, root.val);
    }

    private int dfs1(TreeNode root, int max, int min) {
        if (root == null) {
            return max - min;
        }
        max = Math.max(max, root.val);
        min = Math.min(min, root.val);
        int left = dfs1(root.left, max, min);
        int right = dfs1(root.right, max, min);
        return Math.max(left, right);
    }


    int ans;

    //  全局变量维护结果
    public int maxAncestorDiff2(TreeNode root) {
        ans = 0;
        dfs2(root, root.val, root.val);
        return ans;
    }

    private void dfs2(TreeNode root, int max, int min) {
        if (root == null) {
            return;
        }
        int possibleRes = Math.max(Math.abs(root.val - max), Math.abs(root.val - min));
        ans = Math.max(ans, possibleRes);

        max = Math.max(max, root.val);
        min = Math.min(min, root.val);
        dfs2(root.left, max, min);
        dfs2(root.right, max, min);
    }
}
