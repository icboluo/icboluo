package com.icboluo.leetcode.after_0600;

import com.icboluo.common.TreeNode;

class N0897_二叉树扁平化 {
    public static void main(String[] args) {
        N0897_二叉树扁平化 cla = new N0897_二叉树扁平化();
        Integer[] arr = {5, 3, 6, 2, 4, null, 8, 1, null, null, null, null, null, 7, 9};
        TreeNode treeNode = new TreeNode(arr);
        treeNode.print();
        TreeNode ans = cla.increasingBST2(treeNode);
        ans.print();
    }

    TreeNode cur;

    private TreeNode increasingBST1(TreeNode root) {
        TreeNode ans = new TreeNode();
        cur = ans;
        inorder1(root);
        return ans.right;
    }

    private void inorder1(TreeNode root) {
        if (root == null) {
            return;
        }
        // 中序遍历，前置节点的right等于cur
        inorder1(root.left);
        root.left = null;
        cur.right = root;
        cur = root;
        inorder1(root.right);
    }

    TreeNode pre;

    /**
     * 新建树节点
     *
     * @param root
     * @return
     */
    private TreeNode increasingBST2(TreeNode root) {
        TreeNode ans = new TreeNode();
        pre = ans;
        inorder2(root);
        return ans.right;
    }

    private void inorder2(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder2(root.left);
        pre.right = new TreeNode(root.val);
        pre = pre.right;
        inorder2(root.right);
    }
}
