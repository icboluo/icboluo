package com.icboluo.leetcode.tree.中序遍历;

import com.icboluo.common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author icboluo
 * @since 2025-01-01 15:38
 */
public class a_中序遍历 {
    /**
     * 中序遍历
     */
    public List<TreeNode> inorder(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            // 这里的while只是为了找到最左节点，可以使用if、else的形式取消while
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            list.add(root);
            root = root.right;
        }
        return list;
    }
}
