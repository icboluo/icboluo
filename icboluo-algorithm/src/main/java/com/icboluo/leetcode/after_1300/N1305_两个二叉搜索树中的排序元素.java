package com.icboluo.leetcode.after_1300;

import com.icboluo.common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author icboluo
 * @since 2022-07-10 19:25
 */
class N1305_两个二叉搜索树中的排序元素 {
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        List<Integer> list = new ArrayList<>();
        while (!stack1.isEmpty() || !stack2.isEmpty() || root1 != null || root2 != null) {
            while (root1 != null) {
                stack1.push(root1);
                root1 = root1.left;
            }
            while (root2 != null) {
                stack2.push(root2);
                root2 = root2.left;
            }
            // 此块包含中序遍历，结果为有序数组
            if (stack2.isEmpty() || (!stack1.isEmpty() && stack1.peek().val <= stack2.peek().val)) {
                root1 = stack1.pop();
                list.add(root1.val);
                root1 = root1.right;
            } else {
                root2 = stack2.pop();
                list.add(root2.val);
                root2 = root2.right;
            }
        }
        return list;
    }
}
