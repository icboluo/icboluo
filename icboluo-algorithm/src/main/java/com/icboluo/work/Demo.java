package com.icboluo.work;

import com.icboluo.common.TreeNode;

/**
 * @author icboluo
 * @date 2021-56-09 23:56
 */
public class Demo {
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
