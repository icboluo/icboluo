package com.icboluo.leetcode.after_0300;

import com.icboluo.common.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author icboluo
 * @since 2021-15-22 13:15
 */
class N0337_打家劫舍 {
    public int rob(TreeNode root) {
        return rob2(root);
    }

    private final Map<TreeNode, Integer> cache = new HashMap<>();

    public int rob1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (cache.containsKey(root)) {
            return cache.get(root);
        }
        int left = 0;
        if (root.left != null) {
            left += rob1(root.left.left) + rob1(root.left.right);
        }
        int right = 0;
        if (root.right != null) {
            right += rob1(root.right.left) + rob1(root.right.right);
        }
//        如果抢，则下个节点不能抢
        int rob = root.val + left + right;
//        如果不抢，则下个节点随意
        int notRob = rob1(root.left) + rob1(root.right);
        int max = Math.max(rob, notRob);
        cache.put(root, max);
        return max;
    }

    public int rob2(TreeNode root) {
        int[] initArr = new int[2];
//        arr[0]代表不抢的最大值，arr[1]代表抢的最大值
        int[] arr = rob2SubFun(root, initArr);
        return Math.max(arr[0], arr[1]);
    }

    private int[] rob2SubFun(TreeNode root, int[] arr) {
        if (root == null) {
            return new int[2];
        }
        int[] left = rob2SubFun(root.left, arr);
        int[] right = rob2SubFun(root.right, arr);

//        抢
        int rob = root.val + left[0] + right[0];
//        不抢
        int notRob = arr[0] + Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return new int[]{notRob, rob};
    }
}
