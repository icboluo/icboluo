package com.icboluo.leetcode;

import com.icboluo.common.TreeNode;

/**
 * @author icboluo
 * @date 2022-03-25 19:40
 */
public class N0222_节点数量 {
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
