package com.icboluo.leetcode.tree;

import com.icboluo.common.TreeNode;

/**
 * @author icboluo
 * @since 2022-04-05 22:45
 */
class N0701_二叉查找树新增 {
    public static void main(String[] args) {
        var cla = new N0701_二叉查找树新增();
        TreeNode root = new TreeNode(40, 20, 60, 10, 30, 50, 70);
        root.print();
        TreeNode treeNode = cla.insertIntoBST(root, 25);
        treeNode.print();
    }

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        TreeNode cur = root;
        while (cur != null) {
            if (val > cur.val) {
                if (cur.right == null) {
                    cur.right = new TreeNode(val);
                    break;
                }
                cur = cur.right;
            } else {
                if (cur.left == null) {
                    cur.left = new TreeNode(val);
                    break;
                }
                cur = cur.left;
            }
        }
        return root;
    }
}
