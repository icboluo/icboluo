package com.icboluo.leetcode.onethousand;

import com.icboluo.common.TreeNode;

/**
 * @author icboluo
 * @since 2022-07-10 20:05
 */
public class N1123_最低公共祖先 {

    public TreeNode lcaDeepestLeaves(TreeNode root) {
        return dfs(root, 0).parent;
    }

    // TODO not understand
    private Pair dfs(TreeNode root, int depth) {
        if (root == null) {
            return new Pair(null, depth);
        }
        Pair left = dfs(root.left, depth + 1);
        Pair right = dfs(root.right, depth + 1);
        // 如果公共祖先的深度一样，则返回当前节点
        if (left.depth == right.depth) {
            return new Pair(root, left.depth);
        } else {
            // 首先，会找到最深的节点，然后回溯
            return left.depth > right.depth ? left : right;
        }
    }


    static class Pair {

        /**
         * 最低公共祖先
         */
        TreeNode parent;

        /**
         * 当前节点的深度
         */
        int depth;

        public Pair(TreeNode parent, int depth) {
            this.parent = parent;
            this.depth = depth;
        }
    }
}

