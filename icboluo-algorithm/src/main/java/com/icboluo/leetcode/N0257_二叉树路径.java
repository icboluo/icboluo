package com.icboluo.leetcode;

import com.icboluo.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author icboluo
 * @date 2022-03-25 18:37
 */
public class N0257_二叉树路径 {

    List<List<TreeNode>> ans;

    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        ans = new ArrayList<>();
        path(root, new ArrayList<>());
        return null;
    }

    private void path(TreeNode root, List<TreeNode> list) {
        if (root.left == null && root.right == null) {
            ans.add(new ArrayList<>(list));
        }
        if (root.left != null) {
            list.add(root.left);
            path(root.left, list);
        }
        if (root.right != null) {
            list.add(root.right);
            path(root.right, list);
        }
    }
}
