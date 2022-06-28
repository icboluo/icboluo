package com.icboluo.leetcode.onethousand;

import com.icboluo.common.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * @author icboluo
 * @since 2022-06-28 23:02
 */
public class N1382_二叉搜索树转换为平衡二叉树 {

    List<TreeNode> sortList;

    public TreeNode balanceBST(TreeNode root) {
        sortList = new LinkedList<>();
        inorderTraverse(root);
        return sortListToBst(0, sortList.size() - 1);
    }

    private TreeNode sortListToBst(int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (start + end) / 2;
        TreeNode root = sortList.get(mid);
        root.left = sortListToBst(start, mid - 1);
        root.right = sortListToBst(mid + 1, end);
        return root;
    }

    private void inorderTraverse(TreeNode root) {
        if (root == null) {
            return;
        }
        inorderTraverse(root.left);
        sortList.add(root);
        inorderTraverse(root.right);
    }
}
