package com.icboluo.leetcode;

import com.icboluo.common.TreeNode;

/**
 * @author icboluo
 * @since 2022-03-28 22:58
 */
public class N0235_0236_二叉树公共祖先 {

    /**
     * 0235 二叉搜索树公共祖先
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return lowestCommonAncestor2(root, p, q);
    }

    private TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        int pComp = p.val - root.val;
        int qComp = q.val - root.val;
//        等于0代表一个是另一个的父节点
        if (pComp * qComp <= 0) {
            return root;
        }
//        同号
        if (qComp > 0) {
            return lowestCommonAncestor1(root.right, p, q);
        } else {
            return lowestCommonAncestor1(root.left, p, q);
        }
    }

    private TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        while (true) {
            int pComp = p.val - root.val;
            int qComp = q.val - root.val;
            if (pComp * qComp <= 0) {
                return root;
            }
            if (qComp > 0) {
                root = root.right;
            } else {
                root = root.left;
            }
        }
    }

    /**
     * TODO 二叉树公共祖先
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor0236(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        TreeNode left = lowestCommonAncestor0236(root.left, p, q);
        TreeNode right = lowestCommonAncestor0236(root.right, p, q);
//        均不为空，说明左右均存在元素
        if (left != null && right != null) {
            return root;
        }
        if (left != null) {

        }
        return null;
    }
}
