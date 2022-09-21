package com.icboluo.leetcode.onethousand;

import com.icboluo.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.IntStream;

/**
 * @author icboluo
 * @since 2022-07-10 19:38
 */
class N1161_二叉树的最大级别和 {
    public int maxLevelSum(TreeNode root) {
        return maxLevelSum2(root);
    }

    public int maxLevelSum1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int ans = 0;
        int ansMax = Integer.MIN_VALUE;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int count = 0;
        while (!queue.isEmpty()) {
            count++;
            int temp = 0;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                assert poll != null;
                temp += poll.val;
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
            }
            if (temp > ansMax) {
                ansMax = temp;
                ans = count;
            }
        }
        return ans;
    }

    private List<Integer> list;

    public int maxLevelSum2(TreeNode root) {
        list = new ArrayList<>();
        dfs(root, 0);
        return 1 + IntStream.range(0, list.size()).reduce(0, (a, b) -> list.get(a) >= list.get(b) ? a : b);
    }

    private void dfs(TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        // 标准的dfs level
        if (list.size() == depth) {
            // 第一次用初始值
            list.add(root.val);
        } else {
            // 其余情况使用一般处理
            list.set(depth, list.get(depth) + root.val);
        }
        dfs(root.left, depth + 1);
        dfs(root.right, depth + 1);
    }

    public static void main(String[] args) {
        N1161_二叉树的最大级别和 cla = new N1161_二叉树的最大级别和();
        TreeNode treeNode = new TreeNode(new Integer[]{1, 1, 0, 7, -8, -7, 9});
        int i = cla.maxLevelSum(treeNode);
        System.out.println("i = " + i);
    }
}
