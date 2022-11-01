package com.icboluo.leetcode.after_1000;

import com.icboluo.common.TreeNode;

/**
 * @author icboluo
 * @since 2022-05-29 17:22
 */
class N1315_偶值祖父节点 {
    public int sumEvenGrandparent(TreeNode root) {
        // 标准的动态扩展树，让节点和他的parent产生关系
        return dfs(root, 1, 1);
    }

    private int dfs(TreeNode root, int p, int gp) {
        if (root == null) {
            return 0;
        }
        int left = dfs(root.left, root.val, p);
        int right = dfs(root.right, root.val, p);
        return left + right + gp % 2 == 0 ? root.val : 0;
    }
}
