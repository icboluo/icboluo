package com.icboluo.othersource;

import com.icboluo.common.TreeNode;

import java.util.Stack;

/**
 * @author icboluo
 * @date 2021-21-14 13:21
 */
public class DD {
    public int sumOfLeftLeaves(TreeNode root) {
        return sumOfLeftLeaves1(root);
    }

    public int sumOfLeftLeaves1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int res = 0;
        if (root.left != null) {
            if (root.left.left == null && root.left.right == null) {
                res += root.left.val;
            } else {
                res += sumOfLeftLeaves1(root.left);
            }
        }
//        这一块交给下层去判断，要不然不是最优子结构，没必要在这里
/*        if (root.right != null) {
            if (root.right.left != null || root.right.right != null) {
                res += sumOfLeftLeaves1(root.right);
            }
        }*/
        res += sumOfLeftLeaves1(root.right);
        return res;
    }

    public int sumOfLeftLeaves2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int res = 0;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        if (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            if (cur.left != null) {
                if (cur.left.left == null || cur.left.right == null) {
                    res += root.left.val;
                } else {
                    stack.push(cur.left);
                }
            }
            if (cur.right != null) {
                if (root.right.left != null || root.right.right != null) {
                    stack.push(cur.right);
                }
            }
        }
        return res;
    }
}
