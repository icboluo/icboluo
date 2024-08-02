package com.icboluo.leetcode.tree;

import com.icboluo.common.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author icboluo
 * @since 2021-25-04 13:25
 */
class N0971_翻转二叉树判断相等 {
    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        ans = new ArrayList<>();
        idx = 0;
        dfs(root, voyage);
        if (!ans.isEmpty() && ans.get(0).equals(-1)) {
            ans.clear();
            ans.add(-1);
        }
        return ans;
    }

    List<Integer> ans;
    int idx;

    private void dfs(TreeNode root, int[] voyage) {
        if (root == null) {
            return;
        }
//        判断节点值是否相等
        if (root.val != voyage[idx++]) {
            ans.clear();
            ans.add(-1);
            return;
        }
//        左边不相等，翻转树比较，相等，下一层
        if (idx < voyage.length && root.left != null && root.left.val != voyage[idx]) {
            ans.add(root.val);
            dfs(root.right, voyage);
            dfs(root.left, voyage);
        } else {
            dfs(root.left, voyage);
            dfs(root.right, voyage);
        }
    }


    public List<Integer> flipMatchVoyage2(TreeNode root, int[] voyage) {
        ans = new ArrayList<>();
        idx = 0;
        boolean b = dfs2(root, voyage);
        return b ? ans : Collections.singletonList(-1);
    }

    private boolean dfs2(TreeNode root, int[] voyage) {
        if (root == null) {
            return true;
        }
        if (root.val != voyage[idx]) {
            return false;
        }
        idx++;
//        左边不为null，这里做答案汇总，应该是这种遍历顺序
        if (root.left != null && root.left.val != voyage[idx]) {
            ans.add(root.val);
            return dfs2(root.right, voyage) && dfs2(root.left, voyage);
        }
//        前半部分是否没有必要...
        return dfs2(root.left, voyage) && dfs2(root.right, voyage);
    }
}
