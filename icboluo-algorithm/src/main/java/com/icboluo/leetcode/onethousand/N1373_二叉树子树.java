package com.icboluo.leetcode.onethousand;

import com.icboluo.common.TreeNode;

/**
 * @author icboluo
 * @since 2022-05-29 16:48
 */
class N1373_二叉树子树 {

    int maxSum = 0;

    public int maxSumBST(TreeNode root) {
        postOrder(root);
        return maxSum;
    }

    private int[] postOrder(TreeNode root) {
        if (root == null) {
            return new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
        }
        int[] left = postOrder(root.left);
        int[] right = postOrder(root.right);
        // 如果左子树不是二叉搜索树...或者根节点的值小于左子树的最小值（是不合理的，说明构成不了二叉搜索树
        if (left == null || right == null || root.val < left[1] || root.val > right[0]) {
            return null;
        }
        // 否则是正确的，后序遍历真正有效的地方
        int sum = root.val + left[2] + right[2];
        maxSum = Math.max(maxSum, sum);
        // 重新回顾查找树
        int min = Math.min(root.val, left[0]);
        int max = Math.max(root.val, right[1]);
        return new int[]{min, max, sum};
    }
}
