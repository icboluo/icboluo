package com.icboluo.leetcode.tree.中序遍历;

import com.icboluo.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author icboluo
 * @since 2023-05-16 21:25
 */
class N0501_二叉搜索树中的众数 {
    public int[] findMode(TreeNode root) {
        if (root == null) {
            return new int[0];
        }

        List<Integer> res = new ArrayList<>();
        inorder(root, res);
        return res.stream().mapToInt(Integer::intValue).toArray();
    }

    TreeNode pre;

    int count = 1;

    int max = 1;

    private void inorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        inorder(root.left, res);
        if (pre != null) {
            if (pre.val == root.val) {
                count++;
            } else {
                count = 1;
            }
        }
        if (count > max) {
            max = count;
            res.clear();
            res.add(root.val);
        } else if (count == max) {
            res.add(root.val);
        } else {
            // 数量没有达到最大的时候，结果不变
        }
        pre = root;

        inorder(root.right, res);
    }
}
