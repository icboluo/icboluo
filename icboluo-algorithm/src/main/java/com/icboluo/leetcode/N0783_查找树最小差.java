package com.icboluo.leetcode;

import com.icboluo.common.TreeNode;

/**
 * @author icboluo
 * @since 2022-04-05 22:55
 */
public class N0783_查找树最小差 {

    int ans = Integer.MAX_VALUE;
    Integer pre;

    public int minDiffInBST(TreeNode root) {
        m(root);
        return ans;
    }

    private void m(TreeNode root) {
        if (root.left != null) {
            m(root.left);
        }
//        中序遍历即可
        if (pre != null) {
            ans = Math.min(ans, root.val - pre);
        }
        pre = root.val;
        if (root.right != null) {
            m(root.right);
        }
    }
}
