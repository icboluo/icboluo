package com.icboluo.leetcode.after_fivehundred;

import com.icboluo.common.TreeNode;

/**
 * @author icboluo
 * @since 2022-03-29 22:21
 */
public class N0606_构造括号二叉树 {
    public String tree2str1(TreeNode root) {
        if (root == null) {
            return "";
        }
        String left = tree2str(root.left);
        String right = tree2str(root.right);
        String ans = root.val + "";
        if ("".equals(left) && "".equals(right)) {
            return ans;
        }
        if ("".equals(left)) {
            return ans + "()(" + right + ")";
        }
        if ("".equals(right)) {
            return ans + "(" + left + ")";
        }
        return ans + "(" + left + ")(" + right + ")";
    }

    public String tree2str(TreeNode root) {
        if (root == null) {
            return "";
        }
        String left = tree2str(root.left);
        String right = tree2str(root.right);
        String ans = root.val + "";
        if ("".equals(left) && "".equals(right)) {
            return ans;
        }
        ans += "(" + left + ")";
        if (!"".equals(right)) {
            ans += "(" + right + ")";
        }
        return ans;
    }
}
