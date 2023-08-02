package com.icboluo.leetcode.tree;

import com.icboluo.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

class N0951_翻转二叉树之后判断是否相等 {
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
        // list的equals原理是比较里面是元素嘛？应该是的，但是这个里面没有重写toString
        return list1.stream().map(treeNode -> treeNode.val).toList().equals(list2.stream().map(treeNode -> treeNode.val).toList());
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
