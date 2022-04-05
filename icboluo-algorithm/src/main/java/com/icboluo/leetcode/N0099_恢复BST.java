package com.icboluo.leetcode;

import com.icboluo.common.TreeNode;

/**
 * @author icboluo
 * @since 2022-02-25 20:29
 */
public class N0099_恢复BST {
    public void recoverTree(TreeNode root) {
//        一个有序数组，交换位置怎么恢复（swap即可），Bst作为有序数组即可
        recover(root);
        int a = fir.val;
        fir.val = sec.val;
        sec.val = a;
    }

    TreeNode fir = null;
    TreeNode sec = null;
    TreeNode pre = new TreeNode(Integer.MIN_VALUE);

    private void recover(TreeNode root) {
        if (root == null) {
            return;
        }
        recover(root.left);
        if (fir == null && root.val < pre.val) {
//            由图可知：中序遍历，第一个的值为前一个值，第二个值为当前值，非常重要
            fir = pre;
        }
        // 这里需要判断sec，可是，其实只要fir！=null即可，因为题中说只有2个
        if (fir != null && root.val < pre.val) {
            sec = root;
        }
        pre = root;
        recover(root.right);
    }
}
