package com.icboluo.leetcode;

import com.icboluo.common.TreeNode;

import java.util.Stack;

/**
 * @author icboluo
 * @date 2021-11-17 23:40
 */
public class N0098_判断二叉查找树 {

    public boolean isValidBST(TreeNode root) {
        return isValidBST2(root);
    }

    public boolean isValidBST1(TreeNode root) {
        return isValidBST1(root, Long.MAX_VALUE, Long.MIN_VALUE);
    }

    private boolean isValidBST1(TreeNode root, long maxValue, long minValue) {
        if (root == null) {
            return true;
        }
        if (root.val >= maxValue || root.val <= minValue) {
            return false;
        }
        return isValidBST1(root.left, root.val, minValue)
                && isValidBST1(root.right, maxValue, root.val);
    }

    public boolean isValidBST2(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            if (cur.left != null) {
                if (cur.val < cur.left.val) {
                    return false;
                }
                stack.push(cur.left);
            }
            if (cur.right != null) {
                if (cur.val > cur.right.val) {
                    return false;
                }
                stack.push(cur.right);
            }
        }
        return true;
    }

    public boolean isValidBST3(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        int max = 0;
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            if (cur.left != null) {
                stack.push(cur.left);
            } else if (!stack.isEmpty()) {
                TreeNode pop = stack.pop();
                if (pop.val < max) {
                    return false;
                }
//                如果栈是空
            } else {
                max = cur.val;
                cur = cur.right;
            }
        }
        return true;
    }
}
