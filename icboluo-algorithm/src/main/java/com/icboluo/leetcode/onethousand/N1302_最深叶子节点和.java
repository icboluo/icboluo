package com.icboluo.leetcode.onethousand;

import com.icboluo.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author icboluo
 * @since 2022-05-29 17:28
 */
class N1302_最深叶子节点和 {
    public int deepestLeavesSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int temp = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            temp = 0;
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
        }
        return temp;
    }
}
