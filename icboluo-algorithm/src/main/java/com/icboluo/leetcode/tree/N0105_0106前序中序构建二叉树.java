package com.icboluo.leetcode.tree;

import com.icboluo.common.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author icboluo
 * @since 2021-11-18 0:07
 */
class N0105_0106前序中序构建二叉树 {
    public static void main(String[] args) {
        var cla = new N0105_0106前序中序构建二叉树();
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};
        cla.buildTree1(preorder, inorder).print();
        TreeNode treeNode = cla.buildTree2(inorder, postorder);
        treeNode.print();
    }

    Map<Integer, Integer> inorderMap;
    int index;

    public TreeNode buildTree1(int[] preorder, int[] inorder) {
        inorderMap = new HashMap<>();
        index = 0;
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return buildTree1(preorder, 0, preorder.length - 1);
    }

    private TreeNode buildTree1(int[] preorder, int left, int right) {
        if (left > right) {
            return null;
        }
        int val = preorder[index++];
        TreeNode root = new TreeNode(val);
        root.left = buildTree1(preorder, left, inorderMap.get(val) - 1);
        root.right = buildTree1(preorder, inorderMap.get(val) + 1, right);
        return root;
    }


    /**
     * N0106_后序中路构建二叉树 FIXME
     *
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || inorder.length != postorder.length) {
            return null;
        }
        inorderMap = new HashMap<>();
        index = postorder.length - 1;
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return buildTree2(postorder, 0, postorder.length - 1);
    }

    private TreeNode buildTree2(int[] postorder, int left, int right) {
        if (left > right) {
            return null;
        }
        int val = postorder[index--];
        TreeNode root = new TreeNode(val);
        root.left = buildTree2(postorder, left, inorderMap.get(val) - 1);
        root.right = buildTree2(postorder, inorderMap.get(val) + 1, right);
        return root;
    }
}
