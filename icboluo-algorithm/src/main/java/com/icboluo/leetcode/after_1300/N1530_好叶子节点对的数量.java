package com.icboluo.leetcode.after_1300;

import com.icboluo.common.TreeNode;

/**
 * @author icboluo
 * @since 2023-03-17 0:33
 */
class N1530_好叶子节点对的数量 {

    int ans;

    /**
     * 如果2个叶子节点的最短路径小于等于 distance，那他们就是一对好叶子节点对 TODO ,don`t understand FIXME ERROR
     *
     * @param root
     * @param distance
     * @return
     */
    public int countPairs(TreeNode root, int distance) {
        ans = 0;
        helper(root, distance);
        return ans;
    }

    private int[] helper(TreeNode root, int distance) {
        if (root == null) {
            return new int[distance + 1];
        }
        // 递归的判断，如果不需要后序的结果，可以把判断尽可能的提前一点；没必要放在left、right后面执行
        int[] temp = new int[distance + 1];
        if (root.left == null && root.right == null) {
            temp[1]++;
            return temp;
        }
        int[] left = helper(root.left, distance);
        int[] right = helper(root.right, distance);
        for (int i = 0; i <= distance; i++) {
            for (int j = 0; j <= distance; j++) {
                if (i + j <= distance) {
                    ans += left[i] * right[j];
                }
            }
        }
        for (int i = 0; i < distance; i++) {
            temp[i + 1] = left[i + 1] + right[i + 1];
        }
        return temp;
    }
}
