package com.icboluo.leetcode.onethousand;

import com.icboluo.common.TreeNode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author icboluo
 * @since 2021-45-19 12:45
 */
public class N0671_二叉树次小值 {
    public static void main(String[] args) {
        Integer[] arr = {2, 2, 5, null, null, 5, 7};
        TreeNode root = new TreeNode(arr);
        N0671_二叉树次小值 cla = new N0671_二叉树次小值();
        int secondMinimumValue = cla.findSecondMinimumValue(root);
        System.out.println("secondMinimumValue = " + secondMinimumValue);
    }

    public int findSecondMinimumValue(TreeNode root) {
        Set<TreeNode> set = new HashSet<>();
        dfs(root, set);
        int min = root.val;
        long ans = Long.MAX_VALUE;
        for (TreeNode temp : set) {
            int val = temp.val;
            if (min == val) {
                continue;
            }
            if (val < min) {
                ans = min;
                min = val;
//                大于min
            } else if (val < ans) {
                ans = val;
            }
        }
        return ans == Long.MAX_VALUE ? -1 : (int) ans;
    }

    private void dfs(TreeNode root, Set<TreeNode> set) {
        if (root == null) {
            return;
        }
        set.add(root);
        dfs(root.left, set);
        dfs(root.right, set);
    }

    /**
     * 放在set中，循环比较依次比较次小值,这个是行不通的，需要删除
     *
     * @param root
     * @return
     */
    public int findSecondMinimumValue2(TreeNode root) {
        Set<TreeNode> set = new HashSet<>();
        dfs(root, set);
        int min = root.val;
        int ans = Integer.MAX_VALUE;
        for (TreeNode temp : set) {
            int cur = temp.val;
            if (cur < min) {
                ans = min;
                min = cur;
            }
        }
        return ans;
    }
}
