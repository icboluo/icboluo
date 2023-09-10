package com.icboluo.leetcode.tree;

import com.icboluo.common.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author icboluo
 * @since 2023-09-10 19:15
 */
class N0230_第k个最小元素 {
    // 二分查找 TODO 完全看不懂 FIXME ERROR
    public int kthSmallest1(TreeNode root, int k) {
        int count = count(root);
        if (k <= count) {
            // 如果当前节点所拥有的节点数较多
            return kthSmallest1(root.left, k);
        } else if (k > count + 1) {
            // 如果总数过小，同时递减节点和k
            return kthSmallest1(root.right, k - 1 - count);
        } else {
            // k==count+1
            return root.val;
        }
    }

    public int count(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + count(root.left) + count(root.right);
    }

    int number = 0;
    int count = 0;

    // DFS中序遍历，挺简单的，这玩意相当于顺序遍历 optimize 遍历
    public int kthSmallest2(TreeNode root, int k) {
        count = k;
        dfs(root);
        return number;
    }

    private void dfs(TreeNode root) {
        if (root.left != null) {
            dfs(root.left);
        }
        count--;
        if (count == 0) {
            number = root.val;
            return;
        }
        if (root.right != null) {
            dfs(root.right);
        }
    }

    // DFS 有序迭代，这块代码很好玩，写起来相当吃力
    public int kthSmallest3(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        // 将left节点存入栈
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
        // 从最小的元素开始
        while (k != 0) {
            TreeNode pop = stack.pop();
            k--;
            if (k == 0) {
                return pop.val;
            }
            TreeNode right = pop.right;
            while (right != null) {
                stack.push(right);
                right = right.left;
            }
        }
        return -1;
    }
}
