package com.icboluo.leetcode.onethousand;

import com.icboluo.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author icboluo
 * @since 2021-25-04 13:25
 */
public class N0971 {

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
}
