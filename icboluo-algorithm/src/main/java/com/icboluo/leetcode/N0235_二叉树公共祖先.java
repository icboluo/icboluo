package com.icboluo.leetcode;

import com.icboluo.common.TreeNode;

/**
 * @author icboluo
 * @date 2022-03-28 22:58
 */
public class N0235_二叉树公共祖先 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        int pComp = p.val - root.val;
        int qComp = q.val - root.val;
//        等于0代表一个是另一个的父节点
        if (pComp * qComp <= 0) {
            return root;
        }
//        同号
        if (qComp > 0) {
            return lowestCommonAncestor(root.right, p, q);
        } else {
            return lowestCommonAncestor(root.left, p, q);
        }
    }
}
