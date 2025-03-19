package com.icboluo.leetcode.tree;

import com.icboluo.common.TreeNode;

import java.util.ArrayDeque;

/**
 * @author icboluo
 * @since 2025-03-19 23:06
 */
class N0100_相似树 {
    public static void main(String[] args) {
        var cla = new N0100_相似树();
        TreeNode root1 = new TreeNode(1, 2, 3);
        TreeNode root2 = new TreeNode(1, 2, 3);

        System.out.println(cla.isSameTree(root1, root2));
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null) {
            return false;
        }
        if (q == null) {
            return false;
        }
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }


    public boolean isSameTree2(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (!check(p, q)) {
            return false;
        }
        ArrayDeque<TreeNode> pQue = new ArrayDeque<>();
        ArrayDeque<TreeNode> qQue = new ArrayDeque<>();
        pQue.addLast(p);
        qQue.addLast(q);
        while (!pQue.isEmpty()) {
//            先校验本身
            TreeNode pFir = pQue.removeFirst();
            TreeNode qFir = qQue.removeFirst();
            if (!check(pFir, qFir)) {
                return false;
            }
//            再校验两边
            if (!check(pFir.left, qFir.left)) {
                return false;
            }
            if (pFir.left != null) {
                pQue.addLast(pFir.left);
                qQue.addLast(qFir.left);
            }
            if (!check(pFir.right, qFir.right)) {
                return false;
            }
            if (pFir.right != null) {
                pQue.addLast(pFir.right);
                qQue.addLast(qFir.right);
            }
        }
        return true;
    }

    public boolean check(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        return p.val == q.val;
    }
}
