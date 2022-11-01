package com.icboluo.leetcode.after_0000;

import com.icboluo.common.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author icboluo
 * @since 2021-11-18 0:07
 */
class N0105_前序中序构建二叉树 {
    Map<Integer, Integer> inorderMap;
    int index;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        inorderMap = new HashMap<>();
        index = 0;
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return buildTree(preorder, 0, preorder.length - 1);
    }

    private TreeNode buildTree(int[] preorder, int left, int right) {
        if (left > right) {
            return null;
        }
        int val = preorder[index++];
        TreeNode root = new TreeNode(val);
        root.left = buildTree(preorder, left, inorderMap.get(val) - 1);
        root.right = buildTree(preorder, inorderMap.get(val) + 1, right);
        return root;
    }
}
