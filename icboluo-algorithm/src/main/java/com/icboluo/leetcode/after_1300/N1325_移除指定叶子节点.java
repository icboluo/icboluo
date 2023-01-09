package com.icboluo.leetcode.after_1300;

import com.icboluo.common.TreeNode;

/**
 * @author icboluo
 * @since 2022-05-29 17:18
 */
class N1325_移除指定叶子节点 {
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        if (root == null) {
            return null;
        }
        root.left = removeLeafNodes(root.left, target);
        root.right = removeLeafNodes(root.right, target);
        if (root.left == null && root.right == null && root.val == target) {
            return null;
        }
        return root;
    }
}
