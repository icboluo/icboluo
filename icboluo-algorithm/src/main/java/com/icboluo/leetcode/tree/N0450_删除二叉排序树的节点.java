package com.icboluo.leetcode.tree;

import com.icboluo.common.TreeNode;

/**
 * @author icboluo
 * @since 2024-07-23 下午9:34
 */
class N0450_删除二叉排序树的节点 {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else {
            // optimize 二叉排序树的删除
            // 如果只有左子树，返回左子树
            if (root.right == null) {
                return root.left;
            } else if (root.left == null) {
                return root.right;
            }
            final TreeNode min = findMin(root.right);
            // 节点有左子树和右子树 - 找到右子树中的最小值，将该值设置为当前找到的节点，然后递归删除右子树中的最小值
            root.val = min.val;
            root.right = deleteNode(root.right, min.val);

            // 这种方案更好，但是更难理解；将左子树放到右小的左边，然后将整块右子树迁移至当前节点位置处
//            min.left = root.left;
//            return root.right;
        }
        return root;
    }

    private TreeNode findMin(TreeNode root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }
}
