package com.icboluo.leetcode.after_0600;

import com.icboluo.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author icboluo
 * @since 2022-03-29 23:53
 */
 class N0655_打印二叉树 {
    List<List<String>> ans;

    //    递归强写
    public List<List<String>> printTree(TreeNode root) {
        ans = new ArrayList<>();
        int high = high(root);
        int eleNum = (int) Math.pow(2, high) - 1;
        for (int i = 0; i < high; i++) {
            List<String> tempList = new ArrayList<>();
            for (int j = 0; j < eleNum; j++) {
                tempList.add("");
            }
            ans.add(tempList);
        }
        set(root, 0, 0, eleNum - 1);
        return ans;
    }

    private void set(TreeNode root, int level, int left, int right) {
        if (root == null) {
            return;
        }
        int idx = (left + right) / 2;
        ans.get(level).set(idx, root.val + "");
        set(root.left, level + 1, left, idx - 1);
        set(root.right, level + 1, idx + 1, right);
    }

    public int high(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = high(root.left);
        int right = high(root.right);
        return Math.max(left, right) + 1;
    }

    public static void main(String[] args) {
        N0655_打印二叉树 cla = new N0655_打印二叉树();
        Integer[] arr = {1, 2};
        TreeNode treeNode = new TreeNode(arr);
        List<List<String>> lists = cla.printTree(treeNode);
        System.out.println("lists = " + lists);
    }
}
