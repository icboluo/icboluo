package com.icboluo.leetcode.after_0000;

import com.icboluo.common.TreeNode;

import java.util.*;

/**
 * @author icboluo
 * @since 2022-03-28 23:24
 */
class N0144_N0145_前序中序遍历 {
    List<Integer> ans;

    public List<Integer> preorderTraversal(TreeNode root) {
        ans = new ArrayList<>();
        preorder1(root);
        return ans;
    }

    private void preorder1(TreeNode root) {
        if (root == null) {
            return;
        }
        ans.add(root.val);
        preorder1(root.left);
        preorder1(root.right);
    }

    private void preorder2(TreeNode root) {
        if (root == null) {
            return;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
//            这里也可以直接判断pop，就不用判断left和right了
            ans.add(pop.val);
            if (pop.right != null) {
                stack.push(pop.right);
            }
            if (pop.left != null) {
                stack.push(pop.left);
            }
        }
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        ans = new ArrayList<>();
        postorder1(root);
        return ans;
    }

    private void postorder1(TreeNode root) {
        if (root == null) {
            return;
        }
        postorder1(root.left);
        postorder1(root.right);
        ans.add(root.val);
    }

    //    TODO error
    private void postorder2(TreeNode root) {
        if (root == null) {
            return;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            ans.add(pop.val);
            if (pop.right != null) {
                stack.push(pop.right);
            }
            if (pop.left != null) {
                stack.push(pop.left);
            }
        }
    }
}
