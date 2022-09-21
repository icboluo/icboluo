package com.icboluo.leetcode.after_fivehundred;

import com.icboluo.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author icboluo
 * @since 2022-04-05 23:12
 */
class N0897_搜索树转换成链表 {
    public static void main(String[] args) {
        Integer[] arr = {5, 3, 6, 2, 4, null, 8, 1, null, null, null, 7, 9};
        TreeNode treeNode = new TreeNode(arr);
        N0897_搜索树转换成链表 cla = new N0897_搜索树转换成链表();
        TreeNode treeNode1 = cla.increasingBST(treeNode);
        System.out.println("treeNode1 = " + treeNode1);
    }

    public TreeNode increasingBST(TreeNode root) {
        return increasingBST2(root);
    }

    public TreeNode increasingBST1(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        inorder(root, list);
        TreeNode newRoot = new TreeNode(0);
        TreeNode pre = newRoot;
        for (TreeNode treeNode : list) {
            pre.right = new TreeNode(treeNode.val);
            pre = pre.right;
        }
        return newRoot.right;
    }

    private void inorder(TreeNode root, List<TreeNode> list) {
        if (root == null) {
            return;
        }
        inorder(root.left, list);
        list.add(root);
        inorder(root.right, list);
    }

    TreeNode pre = new TreeNode(0);

    /**
     * TODO this fun is error
     *
     * @param root
     * @return
     */
    public TreeNode increasingBST2(TreeNode root) {
        inorder2(root);
        return pre.right;
    }

    private void inorder2(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder2(root.left);
//        少了一步移动，前驱节点不动了
        pre.right = new TreeNode(root.val);
        inorder2(root.right);
    }

    public TreeNode increasingBST3(TreeNode root) {
//        多参看起来吃力
        TreeNode newRoot = new TreeNode(0);
        inorder3(root, newRoot);
        return newRoot.right;
    }

    private void inorder3(TreeNode root, TreeNode pre) {
        if (root == null) {
            return;
        }
        TreeNode treeNode = new TreeNode(root.val);
        inorder3(root.left, treeNode);
        pre.right = treeNode;
        inorder3(root.right, treeNode);
    }


    public TreeNode increasingBST4(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode newHead = new TreeNode(root.val);
        newRoot = newHead;
        m(root);
        return newHead.right;
    }

    TreeNode newRoot;

    private void m(TreeNode root) {
        if (root == null) {
            return;
        }
        m(root.left);
        newRoot.right = new TreeNode(root.val);
        newRoot = newRoot.right;
        m(root.right);
    }
}
