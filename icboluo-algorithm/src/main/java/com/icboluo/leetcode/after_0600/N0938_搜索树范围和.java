package com.icboluo.leetcode.after_0600;

import com.icboluo.common.TreeNode;

/**
 * @author icboluo
 * @since 2022-04-05 23:24
 */
class N0938_搜索树范围和 {
    public int rangeSumBST1(TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        }
        // 2.如果当前的值比较大，那sum应该在左子树中，可以直接ret；比较小，右子树中；刚好，sum+root.val 没必要定义全局变量，用方法返回值
        if (root.val > high) {
            return rangeSumBST1(root.left, low, high);
        }
        if (root.val < low) {
            return rangeSumBST1(root.right, low, high);
        }
        return rangeSumBST1(root.left, low, high) + rangeSumBST1(root.right, low, high) + root.val;
    }

    int sum = 0;

    public int rangeSumBST2(TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        }
        // 1.嗯写法，如果合理，update opt
        if (root.val >= low && root.val <= high) {
            sum += root.val;
        }
        rangeSumBST2(root.left, low, high);
        rangeSumBST2(root.right, low, high);
        return sum;
    }

    public int rangeSumBST3(TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        }
        if (root.val > high) {
            sum += rangeSumBST3(root.left, low, high);
        }
        if (root.val < low) {
            sum += rangeSumBST3(root.right, low, high);
        }
        if (root.val >= low && root.val <= high) {
            sum += root.val;
        }
        return sum;
    }
}
