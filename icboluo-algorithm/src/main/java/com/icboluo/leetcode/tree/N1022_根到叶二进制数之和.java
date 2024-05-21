package com.icboluo.leetcode.tree;


import com.icboluo.common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author icboluo
 * @since 2024-02-26 22:43
 */
class N1022_根到叶二进制数之和 {
    public static void main(String[] args) {
        var cla = new N1022_根到叶二进制数之和();
        Integer[] arr = {1, 1};
        TreeNode root = new TreeNode(arr);
        cla.sumRootToLeaf(root);
        System.out.println(cla.ans);

        System.out.println(cla.dfs2(root, 0));
    }

    int ans = 0;

    Deque<TreeNode> stack;

    /**
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
        dfs1(root);
        return ans;
    }

    /**
     * 路径收集方法
     * optimize dfs的2种用法，用返回值和不用返回值
     *
     * @param root 根节点
     */
    private void dfs1(TreeNode root) {
        if (root == null) {
            return;
        }
        // 这里用栈不如用 linkedList
        stack.push(root);
        if (root.left == null && root.right == null) {
            List<TreeNode> list = new ArrayList<>(stack);
            String str = "";
            for (TreeNode treeNode : list) {
                str = treeNode.val + str;
            }
            ans += Integer.parseInt(str, 2);
        } else {
            dfs1(root.left);
            dfs1(root.right);
        }
        stack.pop();
    }

    // 计算方法
    public int dfs2(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        int temp = sum * 2 + root.val;
        if (root.left == null && root.right == null) {
            return temp;
        } else {
            return dfs2(root.left, temp) + dfs2(root.right, temp);
        }
    }
}
