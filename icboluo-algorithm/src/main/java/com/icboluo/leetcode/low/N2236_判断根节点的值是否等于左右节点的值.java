package com.icboluo.leetcode.low;

import com.icboluo.common.TreeNode;

/**
 * @author icboluo
 * @since 2023-03-30 21:55
 */
class N2236_判断根节点的值是否等于左右节点的值 {
    public boolean checkTree(TreeNode root) {
        return root.val == root.left.val + root.right.val;
    }
}
