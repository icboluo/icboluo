package com.icboluo.leetcode.tree;

import com.icboluo.common.TreeNode;

/**
 * @author icboluo
 * @since 2025-02-18 13:06
 */
class N0110_是否是平衡二叉树 {
    public static void main(String[] args) {
        var cla = new N0110_是否是平衡二叉树();
        TreeNode root = new TreeNode(3, 9, 20, null, null, 15, 7);
        root.print();
        System.out.println(cla.isBalanced(root));

        TreeNode root2 = new TreeNode(1, 2, 2, 3, 3, null, null, 4, 4);
        root2.print();
        System.out.println(cla.isBalanced(root));
    }


    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return height(root) != -1;
    }

    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = height(root.left);
        if (leftHeight == -1) {
            return -1;
        }
        int rightHeight = height(root.right);
        if (rightHeight == -1) {
            return -1;
        }
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        return Math.max(rightHeight, leftHeight) + 1;
    }
}
