package com.icboluo.leetcode.fivehundred;

import com.icboluo.common.TreeNode;

/**
 * @author icboluo
 * @since 2022-06-28 23:11
 */
class N0108_将排序数组转换为平衡二叉树 {
    public TreeNode sortedArrayToBST(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    private TreeNode build(int[] arr, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (start + end) / 2;
        TreeNode root = new TreeNode();
        root.val = arr[mid];
        root.left = build(arr, start, mid - 1);
        root.right = build(arr, mid + 1, end);
        return root;
    }
}
