package com.icboluo.leetcode.after_0000;

import com.icboluo.common.TreeNode;

/**
 * @author icboluo
 * @since 2022-03-28 22:58
 */
class N0235_0236_二叉树公共祖先 {

    /**
     * N0235 二叉搜索树公共祖先
     *
     * @param root 二叉搜索树根节点
     * @param p    节点1
     * @param q    节点2
     * @return 公共祖先
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return lowestCommonAncestor3(root, p, q);
    }

    private TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        int pComp = p.val - root.val;
        int qComp = q.val - root.val;
        // 等于0代表一个是另一个的父节点
        // 这里下面的写法更容易理解，如果某种条件下，循环调用，直到不满足条件，返回结果
        if (pComp * qComp <= 0) {
            return root;
        }
        // 同号
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
            if (pComp * qComp > 0) {
                if (qComp > 0) {
                    root = root.right;
                } else {
                    root = root.left;
                }
            } else {
                return root;
            }
        }
    }

    /**
     * N0236 二叉树的最低共同祖先
     *
     * @param root 二叉树根节点
     * @param p    节点1
     * @param q    节点2
     * @return 公共祖先
     */
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        // 父节点就是某个节点
        if (root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor3(root.left, p, q);
        TreeNode right = lowestCommonAncestor3(root.right, p, q);
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        // 2者均不为null，说明2者同级
        return root;
    }
}
