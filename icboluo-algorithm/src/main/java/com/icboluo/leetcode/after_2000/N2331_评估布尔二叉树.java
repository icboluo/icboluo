package com.icboluo.leetcode.after_2000;

import com.icboluo.common.TreeNode;

/**
 * @author icboluo
 * @since 2023-04-20 22:46
 */
class N2331_评估布尔二叉树 {
    /**
     * 二叉树叶子节点是true|false ,其他节点是 or|and 求运算结果
     *
     * @param root
     * @return
     */
    public boolean evaluateTree(TreeNode root) {
        // false
        if (root.val == 0) {
            return false;
        } else if (root.val == 1) {
            return true;
        }
        int val = root.val;
        boolean left = evaluateTree(root.left);
        boolean right = evaluateTree(root.right);
        // or
        if (val == 2) {
            return left || right;
        } else if (val == 3) {
            return left && right;
        }
        return true;
    }
}
