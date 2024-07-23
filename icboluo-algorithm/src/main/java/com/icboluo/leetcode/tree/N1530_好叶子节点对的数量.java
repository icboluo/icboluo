package com.icboluo.leetcode.tree;

import com.icboluo.common.TreeNode;

/**
 * @author icboluo
 * @since 2023-03-17 0:33
 */
class N1530_好叶子节点对的数量 {

    int ans;

    /**
     * 如果2个叶子节点的最短路径小于等于 distance，那他们就是一对好叶子节点对
     *
     * @param root
     * @param distance
     * @return
     */
    public int countPairs(TreeNode root, int distance) {
        /*

        我们做一个一般意义的推论，一个求最大多少个的推论；
        一般意义上，最大多少个的计算方法应该是 left*right (左边的可能性*右边的可能性)
        function 总可能性: ele
        if 无可能性 -> 0
        def left right  // 分
            left = 左边的可能性
        right = 右边的可能性
        return left * right // 治 后序遍历
        */
        ans = 0;
        helper(root, distance);
        return ans;
    }

    private int[] helper(TreeNode root, int distance) {
        if (root == null) {
            return new int[distance + 1];
        }
        // 记录的是当前节点下面叶子节点的个数，有2个用处，1.用于计算left*right；2.用于向父节点反馈left节点的叶子节点个数
        int[] temp = new int[distance + 1];
        if (root.left == null && root.right == null) {
            temp[1]++;
            return temp;
        }
        int[] left = helper(root.left, distance);
        int[] right = helper(root.right, distance);
        for (int i = 0; i < left.length; i++) {
            for (int j = 0; j < right.length; j++) {
                if (i + j <= distance) {
                    ans += left[i] * right[j];
                }
            }
        }
        for (int i = 0; i < distance; i++) {
            temp[i + 1] = left[i] + right[i];
        }
        return temp;
    }
}
