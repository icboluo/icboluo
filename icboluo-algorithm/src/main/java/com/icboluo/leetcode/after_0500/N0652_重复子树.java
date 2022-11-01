package com.icboluo.leetcode.after_0500;

import com.icboluo.common.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author icboluo
 * @since 2022-03-29 23:05
 */
 class N0652_重复子树 {
    Map<String, Integer> countMap;
    List<TreeNode> ans;

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        return findDuplicateSubtrees1(root);
    }

    public List<TreeNode> findDuplicateSubtrees1(TreeNode root) {
        countMap = new HashMap<>();
        ans = new ArrayList<>();
        subtrees(root);
        return ans;
    }

    public String subtrees(TreeNode root) {
        if (root == null) {
            return null;
        }
        String left = subtrees(root.left);
        String right = subtrees(root.right);
        String cur = root.val + "-" + left + "-" + right;
//         这种写法多了些判断，让我们先加进去就不会为空了
/*        if (countMap.get(cur) != null && countMap.get(cur) == 1) {
            ans.add(root);
        }*/
        countMap.put(cur, countMap.getOrDefault(cur, 0) + 1);
        if (countMap.get(cur) == 2) {
            ans.add(root);
        }
        return cur;
    }

    Map<String, Integer> serialIdMap;

    /**
     * TODO 序列化
     *
     * @param root
     * @return
     */
    public List<TreeNode> findDuplicateSubtrees2(TreeNode root) {
        serialIdMap = new HashMap<>();
        ans = new ArrayList<>();
        subtrees2(root);
        return ans;
    }

    private String subtrees2(TreeNode root) {
        if (root == null) {
            return null;
        }
        String left = subtrees(root.left);
        String right = subtrees(root.right);
        String cur = root.val + "-" + left + "-" + right;
        serialIdMap.put(cur, 1);
        return null;
    }

}
