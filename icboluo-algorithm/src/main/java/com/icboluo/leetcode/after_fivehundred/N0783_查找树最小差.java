package com.icboluo.leetcode.after_fivehundred;

import com.icboluo.common.TreeNode;

/**
 * @author icboluo
 * @since 2022-04-05 22:55
 */
class N0783_查找树最小差 {

    int min = Integer.MAX_VALUE;
    Integer pre;

    public int minDiffInBST(TreeNode root) {
        m(root);
        return min;
    }

    private void m(TreeNode root) {
        if (root.left != null) {
            m(root.left);
        }
//        中序遍历即可
        if (pre != null) {
            min = Math.min(min, root.val - pre);
        }
        pre = root.val;
        if (root.right != null) {
            m(root.right);
        }
    }

    // TODO 看不懂
    private int dfs(TreeNode pre, TreeNode root) {
        if (root == null) {
            return Integer.MAX_VALUE;
        }
        // 保留前驱节点即可，递归过程中OPS ans；方法返回最小的left or right
        int min = Math.abs(pre.val - root.val);
        this.min = Math.min(min, this.min);
        int left = dfs(root, root.left);
        int right = dfs(root, root.right);
        return Math.min(left, right);
    }
}
