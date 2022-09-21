package com.icboluo.leetcode.after_fivehundred;

import com.icboluo.common.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author icboluo
 * @since 2022-03-28 21:31
 */
public class N0515_二叉树每层最大值 {

    public List<Integer> largestValues(TreeNode root) {
        largestValues2(root);
        return ans;
    }

    List<Integer> ans;

    public List<Integer> largestValues1(TreeNode root) {
        ans = new LinkedList<>();
        largestValues1(root, 0);
        return ans;
    }

    private void largestValues1(TreeNode root, int level) {
        if (root == null) {
            return;
        }
//        level 比较高了
        if (level == ans.size()) {
            ans.add(root.val);
        } else if (ans.get(level) < root.val) {
            ans.set(level, root.val);
        }
        largestValues1(root.left, level + 1);
        largestValues1(root.right, level + 1);
    }

    private void largestValues2(TreeNode root) {
        ans = new LinkedList<>();
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int temp = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                assert poll != null;
                temp = Math.max(temp, poll.val);
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
            }
            ans.add(temp);
        }
    }
}
