package com.icboluo.leetcode;


import com.icboluo.common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author icboluo
 * @since 2024-02-26 22:43
 */
class N1022_ {
    int ans = 0;

    Deque<TreeNode> stack;

    /**
     * 瞎写的 FIXME
     * 前序遍历，遍历出从上到下的通路，栈用来回溯，奇怪的问题，有点看不懂
     *
     * @param root
     * @return
     */
    public int sumRootToLeaf(TreeNode root) {
        if (root == null) {
            return 0;
        }
        stack = new ArrayDeque<>();
        dfs(root);
        return ans;
    }

    // 路径收集方法
    private void dfs(TreeNode root) {
        // 这里用栈不如用linkedlist
        stack.push(root);
        if (root.left == null && root.right == null) {
            List<TreeNode> list = new ArrayList<>(stack);
            String str = "";
            for (TreeNode treeNode : list) {
                str = treeNode.val + str;
            }
            ans += Integer.parseInt(str, 2);
        } else {
            dfs(root.left);
            dfs(root.right);
        }
        stack.pop();
    }

    // 计算方法
    public int dfs(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        int temp = sum * 2 + root.val;
        if (root.left == null && root.right == null) {
            return temp;
        } else {
            return dfs(root.left, temp) + dfs(root.right, temp);
        }
    }
}
