package com.icboluo.leetcode.after_1000;

import com.icboluo.common.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author icboluo
 * @since 2022-07-10 20:29
 */
class N1110_删除节点并返回森林 {

    Set<Integer> set;

    List<TreeNode> ans;

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        set = Arrays.stream(to_delete).boxed().collect(Collectors.toSet());
        ans = new ArrayList<>();
        dfs(root, true);
        return ans;
    }

    private TreeNode dfs(TreeNode root, boolean isRoot) {
        if (root == null) {
            return null;
        }
        if (isRoot && !set.contains(root.val)) {
            ans.add(root);
        }
        root.left = dfs(root.left, set.contains(root.val));
        root.right = dfs(root.right, set.contains(root.val));
        return set.contains(root.val) ? null : root;
    }
}
