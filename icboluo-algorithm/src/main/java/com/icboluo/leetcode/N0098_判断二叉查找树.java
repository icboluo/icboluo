package com.icboluo.leetcode;

import com.icboluo.common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author icboluo
 * @date 2021-11-17 23:40
 */
public class N0098_判断二叉查找树 {

    public boolean isValidBST(TreeNode root) {
        return isValidBST4(root);
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

    /**
     * 错误解法：没有用树的特性，左右并排
     *
     * @param root
     * @return
     */
    public boolean isValidBST2(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
//            只判断左右是否匹配，不考虑整体性，不合理
            if (cur.left != null) {
                if (cur.val <= cur.left.val) {
                    return false;
                }
                stack.push(cur.left);
            }
            if (cur.right != null) {
                if (cur.val >= cur.right.val) {
                    return false;
                }
                stack.push(cur.right);
            }
        }
        return true;
    }

    /**
     * 错误解法：树的遍历必须有左和右
     *
     * @param root
     * @return
     */
    public boolean isValidBST3(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        int max = Integer.MIN_VALUE;
        while (true) {
            if (root.left != null) {
                stack.push(root.left);
                root = root.left;
            } else if (!stack.isEmpty()) {
                TreeNode pop = stack.pop();
                if (pop.val < max) {
                    return false;
                }
                max = pop.val;
//                右移：每次左了再右没有意义,放下面更不可能，只能移动一次
            } else {
                break;
            }
        }
        return true;
    }

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
//           这里的while只是为了找到最左节点，可以使用if、else的形式取消while
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

    /**
     * bst 第k个节点的值
     */
    public TreeNode kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (--k == 0) {
                return root;
            }
            root = root.right;
        }
        return null;
    }

    public boolean isValidBST4(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        Integer pre = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (pre != null && root.val <= pre) {
                return false;
            }
            pre = root.val;
            root = root.right;
        }
        return true;
    }
}
