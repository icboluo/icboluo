package com.icboluo.leetcode.after_0500;

import com.icboluo.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author icboluo
 * @since 2021-05-28 13:05
 */
class N0872 {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        dfs(root1, list1);
        dfs(root2, list2);
        return list1.equals(list2);
    }

    private void dfs(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            list.add(root.val);
        }
        dfs(root.left, list);
        dfs(root.right, list);
    }
}
