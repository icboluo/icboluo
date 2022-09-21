package com.icboluo.leetcode.fivehundred;

import com.icboluo.common.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author icboluo
 * @since 2021-11-18 0:30
 */
class N0106_后序中序构建二叉树 {
    Map<Integer, Integer> inorderMap;
    int index;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || inorder.length != postorder.length) {
            return null;
        }
        inorderMap = new HashMap<>();
        index = postorder.length - 1;
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return buildTree(postorder, 0, postorder.length - 1);
    }

    private TreeNode buildTree(int[] postorder, int left, int right) {
        if (left > right) {
            return null;
        }
        int val = postorder[index--];
        TreeNode root = new TreeNode(val);
        root.left = buildTree(postorder, left, inorderMap.get(val) - 1);
        root.right = buildTree(postorder, inorderMap.get(val) + 1, right);
        return root;
    }
}
