package com.icboluo.leetcode.onethousand;

import com.icboluo.common.TreeNode;

/**
 * @author icboluo
 * @since 2022-06-28 23:53
 */
public class N1379_克隆数的节点 {
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if (original == null ) {
            return null;
        }
        if (original == target) {
            return cloned;
        }
        TreeNode left = getTargetCopy(original.left, cloned.left, target);
        if (left != null) {
            return left;
        }
        return getTargetCopy(original.right, cloned.right, target);
    }
}
