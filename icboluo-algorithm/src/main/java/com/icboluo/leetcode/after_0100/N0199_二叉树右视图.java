package com.icboluo.leetcode.after_0100;

import com.icboluo.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author icboluo
 * @since 2022-03-28 23:17
 */
class N0199_二叉树右视图 {
    List<Integer> ans;

    public List<Integer> rightSideView(TreeNode root) {
        ans = new ArrayList<>();
        rightView(root, 0);
        return ans;
    }

    private void rightView(TreeNode root, int level) {
        if (root == null) {
            return;
        }
        if (ans.size() == level) {
            ans.add(root.val);
        }
        rightView(root.right, level + 1);
        rightView(root.left, level + 1);
    }
}
