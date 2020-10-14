package com.icboluo.common;

/**
 * 二叉树树节点，并不对树进行方法抽取，只做算法使用
 *
 * @author icboluo
 * @date 2020-09-27 19:12
 */

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                '}';
    }
}
