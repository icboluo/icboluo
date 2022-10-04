package com.icboluo.leetcode.after_fivehundred;

import com.icboluo.common.TreeNode;

import java.util.TreeSet;

/**
 * @author icboluo
 * @since 2022-03-28 21:22
 */
class N0530_二叉查找树最小差 {
    public int getMinimumDifference(TreeNode root) {
        min = Integer.MAX_VALUE;
        getMinDiff(root);
        return min;
    }

    TreeNode pre;
    int min;

    public void getMinDiff(TreeNode root) {
        if (root == null) {
            return;
        }
        getMinDiff(root.left);
        if (pre != null) {
            min = Math.min(min, root.val - pre.val);
        }
        pre = root;
        getMinDiff(root.right);
    }

    TreeSet<Integer> set;

    // 奇怪，这个方法的返回值并没有使用到
    // 和N0783 一致
    public void getMinDiff2(TreeNode root) {
        if (root == null) {
            return;
        }
        // 规规整整的一次遍历，利用set可以取出最近的元素
        while (!set.isEmpty()) {
            if (set.floor(root.val) != null) {
                min = Math.min(min, root.val - set.floor(root.val));
            }
            if (set.ceiling(root.val) != null) {
                min = Math.min(min, set.ceiling(root.val) - root.val);
            }
        }
        set.add(root.val);
        getMinDiff2(root.left);
        getMinDiff(root.right);
    }

}
