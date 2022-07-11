package com.icboluo.leetcode.onethousand;

import com.icboluo.common.TreeNode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author icboluo
 * @since 2022-07-10 13:12
 */
public class N1457_二叉树伪回文路径 {
    public int pseudoPalindromicPaths(TreeNode root) {
        return dfs(root, new HashSet<>());
    }

    private int dfs(TreeNode root, Set<Integer> set) {
        if (root == null) {
            return set.size() <= 1 ? 1 : 0;
        }
        if (set.contains(root.val)) {
            set.remove(root.val);
        } else {
            set.add(root.val);
        }
        int left = dfs(root.left, new HashSet<>(set));
        int right = dfs(root.right, new HashSet<>(set));
        return left + right;
    }

}
