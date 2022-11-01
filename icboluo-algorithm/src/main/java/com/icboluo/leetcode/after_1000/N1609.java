package com.icboluo.leetcode.after_1000;

import com.icboluo.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author icboluo
 * @since 2022-11-01 13:28
 */
class N1609 {
    public boolean isEvenOddTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int count = 0;
        while (!queue.isEmpty()) {
            boolean even = count % 2 == 0;
            int pre = even ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            int size = queue.size();
            for (int i = size; i > 0; i--) {
                TreeNode poll = queue.poll();
                assert poll != null;
                if (even) {
                    if (poll.val % 2 == 0 || poll.val <= pre) {
                        return false;
                    }
                } else if (!even) {
                    if (poll.val % 2 == 1 || poll.val >= pre) {
                        return false;
                    }
                }
                pre = root.val;
                if (root.left != null) {
                    queue.add(root.left);
                }
                if (root.right != null) {
                    queue.add(root.right);
                }
            }
            count++;
        }
        return true;
    }
}
