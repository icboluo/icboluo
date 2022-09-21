package com.icboluo.leetcode.after_fivehundred;

import com.icboluo.common.TreeNode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author icboluo
 * @since 2022-03-29 23:37
 */
public class N0653_二叉树2数和 {
    Set<Integer> set = new HashSet<>();

    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        if (set.contains(k - root.val)) {
            return true;
        }
        set.add(root.val);
        return findTarget(root.left, k) || findTarget(root.right, k);
    }
}
