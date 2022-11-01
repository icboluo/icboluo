package com.icboluo.leetcode.after_0500;

import com.icboluo.common.Node;

import java.util.*;

/**
 * @author icboluo
 * @since 2021-17-09 13:17
 */
 class N0589_先序遍历 {
    public List<Integer> preorder(Node root) {
        return preorder1(root);
//        return preorder2(root);
    }

    public List<Integer> preorder1(Node root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Stack<Node> stack = new Stack<>();
        stack.add(root);
        List<Integer> ans = new ArrayList<>();
        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            ans.add(pop.val);
            for (int i = pop.children.size() - 1; i >= 0; i--) {
                stack.add(pop.children.get(i));
            }
        }
        return ans;
    }

    public List<Integer> preorder2(Node root) {
        List<Integer> ans = new ArrayList<>();
        dfs(root, ans);
        return ans;
    }

    private void dfs(Node root, List<Integer> ans) {
        if (root == null) {
            return;
        }
        ans.add(root.val);
        for (Node child : root.children) {
            dfs(child, ans);
        }
    }
}
