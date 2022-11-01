package com.icboluo.leetcode.after_0500;

import com.icboluo.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

class N0951_ {
    public boolean flipEquiv1(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }
        if (root1.val != root2.val) {
            return false;
        }
        boolean a = flipEquiv1(root1.left, root2.left);
        boolean b = flipEquiv1(root1.right, root2.right);
        boolean c = flipEquiv1(root1.left, root2.right);
        boolean d = flipEquiv1(root1.right, root2.left);
        return (a && b) || (c && d);
    }

    public boolean flipEquiv2(TreeNode root1, TreeNode root2) {
        List<TreeNode> list1 = new ArrayList<>();
        List<TreeNode> list2 = new ArrayList<>();
        dfs(root1, list1);
        dfs(root2, list2);
        // TODO list的equals原理是比较里面是元素嘛？
        return list1.equals(list2);
    }

    private void dfs(TreeNode root, List<TreeNode> list) {
        if (root == null) {
            return;
        }
        list.add(root);
        int left = 0;
        if (root.left != null) {
            left = root.left.val;
        }
        int right = 0;
        if (root.right != null) {
            right = root.right.val;
        }
        // 排序放进list
        if (left < right) {
            dfs(root.left, list);
            dfs(root.right, list);
        } else {
            dfs(root.right, list);
            dfs(root.left, list);
        }
    }
}
