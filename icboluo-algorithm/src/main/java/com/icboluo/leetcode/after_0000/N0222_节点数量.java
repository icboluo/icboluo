package com.icboluo.leetcode.after_0000;

import com.icboluo.common.TreeNode;

/**
 * @author icboluo
 * @since 2022-03-25 19:40
 */
class N0222_节点数量 {
    int ans;

    public int countNodes(TreeNode root) {
        ans = 0;
        count(root);
        return ans;
    }

    private void count(TreeNode root) {
        if (root == null) {
            return;
        }
        ans++;
        count(root.left);
        count(root.right);
    }
}
