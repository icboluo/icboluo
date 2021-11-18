package com.icboluo.leetcode;

import com.icboluo.common.TreeNode;

import java.util.Stack;

/**
 * @author icboluo
 * @date 2021-11-17 23:55
 */
public class N0104_二叉树最大深度 {

    public int maxDepth(TreeNode root) {
        return maxDepth3(root);
    }

    public int maxDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth1(root.left);
        int right = maxDepth1(root.right);
        return left > right ? left + 1 : right + 1;
    }

    public int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Stack<TreeNode> stack = new Stack<>();
        int max = 0;
        stack.push(root);
        while (!stack.isEmpty()) {
            int size = stack.size();
            for (int i = 0; i < size; i++) {
                TreeNode pop = stack.pop();
                if (pop.left != null) {
                    stack.push(pop.left);
                }
                if (pop.right != null) {
                    stack.push(pop.right);
                }
            }
            max++;
        }
        return max;
    }

    public int maxDepth3(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> depthStack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            int size = stack.size();
            for (int i = 0; i < size; i++) {
                TreeNode pop = stack.pop();
                if (pop.left != null) {
                    stack.push(pop.left);
                    depthStack.push(pop.left);
                }
                if (pop.right != null) {
                    stack.push(pop.right);
                    depthStack.push(pop.right);
                }
            }
        }
        return depthStack.size();
    }
}
