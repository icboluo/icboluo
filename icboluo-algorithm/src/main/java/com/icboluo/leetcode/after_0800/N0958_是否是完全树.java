package com.icboluo.leetcode.after_0800;

import com.icboluo.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

class N0958_是否是完全树 {
    // TODO 不是完全理解
    public boolean isCompleteTree1(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (queue.peek() != null) {
            TreeNode poll = queue.poll();
            if (poll.left == null) {
                if (poll.right != null) {
                    return false;
                }
                break;
            }
            queue.offer(poll.left);
            if (poll.right == null) {
                break;
            } else {
                queue.offer(poll.right);
            }
        }
        // 对最后一层做判断
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            if (poll.left != null || poll.right != null) {
                return false;
            }
        }
        return true;
    }

    public boolean isCompleteTree2(TreeNode root) {
        int count = root.count();
        return help(root, 1, count);
    }

    // 完全二叉树的判断，这个是一个遍历的过程
    private boolean help(TreeNode root, int idx, int count) {
        if (root == null) {
            return true;
        }
        if (idx > count) {
            return false;
        }
        return help(root.left, 2 * idx, count) && help(root.right, 2 * idx + 1, count);
    }
}
