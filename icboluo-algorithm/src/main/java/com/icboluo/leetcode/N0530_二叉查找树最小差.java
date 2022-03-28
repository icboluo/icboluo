package com.icboluo.leetcode;

import com.icboluo.common.TreeNode;

import java.util.Set;

/**
 * @author icboluo
 * @date 2022-03-28 21:22
 */
public class N0530_二叉查找树最小差 {
    public int getMinimumDifference(TreeNode root) {
        ans = Integer.MAX_VALUE;
        getMinDiff(root);
        return ans;
    }

    TreeNode pre;
    int ans;

    public void getMinDiff(TreeNode root) {
        if (root == null) {
            return;
        }
        getMinDiff(root.left);
        if (pre != null) {
            ans = Math.min(ans, root.val - pre.val);
        }
        pre = root;
        getMinDiff(root.right);
    }

    Set<TreeNode> set;

    //TODO SET 硬写
    public void getMinDiff2(TreeNode root) {

    }

}
