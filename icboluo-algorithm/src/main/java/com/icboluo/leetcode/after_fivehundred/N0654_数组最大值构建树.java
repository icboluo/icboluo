package com.icboluo.leetcode.after_fivehundred;

import com.icboluo.common.TreeNode;

/**
 * @author icboluo
 * @since 2022-03-29 23:42
 */
 class N0654_数组最大值构建树 {
    int[] arr;


    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return constructMaximumBinaryTree1(nums);
    }

    public TreeNode constructMaximumBinaryTree1(int[] nums) {
        arr = nums;
        return build(0, arr.length - 1);
    }

    private TreeNode build(int left, int right) {
        if (left > right) {
            return null;
        }
        int max = Integer.MIN_VALUE;
        int maxIdx = -1;
        for (int i = left; i <= right; i++) {
            if (arr[i] > max) {
                max = arr[i];
                maxIdx = i;
            }
        }
        TreeNode root = new TreeNode(max);
        root.left = build(left, maxIdx - 1);
        root.right = build(maxIdx + 1, right);
        return root;
    }

    //    TODO 迭代
    public TreeNode constructMaximumBinaryTree2(int[] nums) {
        arr = nums;
        return build(0, arr.length - 1);
    }
}
