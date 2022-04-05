package com.icboluo.leetcode;

import com.icboluo.common.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author icboluo
 * @since 2022-03-28 21:52
 */
public class N0508_最频繁子树和 {

    Map<Integer, Integer> ans;

    public int[] findFrequentTreeSum(TreeNode root) {
        ans = new HashMap<>();
        findFrequentTree(root);
        int i = ans.values().stream().mapToInt(Integer::intValue).max().orElse(0);
        return ans.entrySet().stream()
                .filter(entry -> entry.getValue() == i)
                .mapToInt(Map.Entry::getKey)
                .toArray();
    }

    private int findFrequentTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = findFrequentTree(root.left);
        int right = findFrequentTree(root.right);
        int cur = left + right + root.val;
        ans.put(cur, ans.getOrDefault(cur, 0) + 1);
        return cur;
    }
}
