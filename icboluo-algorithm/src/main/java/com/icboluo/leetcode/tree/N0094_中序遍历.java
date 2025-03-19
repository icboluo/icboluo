package com.icboluo.leetcode.tree;

import com.icboluo.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * @author icboluo
 * @since 2025-03-19 23:11
 */
class N0094_中序遍历 {

    public static void main(String[] args) {
        var cla = new N0094_中序遍历();
        TreeNode root = new TreeNode(1, 2, 3, 4, 5, null, 8, null, null, 6, 7, 9);
        System.out.println(cla.inorderTraversal(root));
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
//        return inorderTraversal1(root, new LinkedList<>());
        return inorderTraversal2(root);
    }

    public List<Integer> inorderTraversal1(TreeNode root, List<Integer> list) {
        if (root.left != null) {
            inorderTraversal1(root.left, list);
        }
        list.add(root.val);
        if (root.right != null) {
            inorderTraversal1(root.right, list);
        }
        return list;
    }

    public List<Integer> inorderTraversal2(TreeNode root) {
        LinkedList<TreeNode> list = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (true) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else if (!stack.isEmpty()) {
                cur = stack.pop();
                list.add(cur);
                cur = cur.right;
            } else {
                break;
            }
        }
        return list.stream().map(treeNode -> treeNode.val).collect(Collectors.toList());
    }
}
