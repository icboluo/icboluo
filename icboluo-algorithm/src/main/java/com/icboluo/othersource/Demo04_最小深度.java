package com.icboluo.othersource;

import com.icboluo.common.TreeNode;

/**
 * @author icboluo
 * @date 2021-11-17 22:25
 */
public class Demo04_最小深度 {
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        return root.left == null || root.right == null ?
                left + right + 1 : Math.min(left, right) + 1;
    }
}
