package com.icboluo.leetcode.tree;

import com.icboluo.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author icboluo
 * @since 2024-07-23 下午9:31
 */
public class N0662_二叉树的最大宽度 {
    public static void main(String[] args) {
        var demo = new N0662_二叉树的最大宽度();
        TreeNode root = new TreeNode(1, 3, 2, 5, 3, null, 9);
        root.print();
        System.out.println(demo.widthOfBinaryTree(root));

        TreeNode root2 = new TreeNode(1, 3, 2, 5, null, null, 9, 6, null, 7);
        root2.print();
        System.out.println(demo.widthOfBinaryTree(root2));

        TreeNode root3 = new TreeNode(1, 3, 2, 5);
        root3.print();
        System.out.println(demo.widthOfBinaryTree(root3));
    }

    public int widthOfBinaryTree(TreeNode root) {
        return widthOfBinaryTree(root, 0, 1, new ArrayList<>(), new ArrayList<>());
    }

    private int widthOfBinaryTree(TreeNode root, int level, int curIndex, List<Integer> left, List<Integer> right) {
        if (root == null) {
            return 0;
        }
        // 每次遍历都重置一下左右子节点，因为遍历的时候是先left，后right，所以当首次==的时候，先赋值left即可，后序不用更新right
        if (left.size() == level) {
            left.add(curIndex);
            right.add(curIndex);
        } else if (left.size() > level) {
            right.set(level, curIndex);
        }
        // 当前层级的宽度=从最左边的index -> 到最右边的idx  +1
        int cur = right.get(level) - left.get(level) + 1;
        int leftLength = widthOfBinaryTree(root.left, level + 1, 2 * curIndex + 1, left, right);
        int rightLength = widthOfBinaryTree(root.right, level + 1, 2 * curIndex + 2, left, right);
        // optimize 这一块代码非常巧妙，本来是做一个全局变量 max=Math.max(max,cur)的，现在不要全局变量，从底向上遍历，每次更新cur即可
        return Math.max(cur, Math.max(leftLength, rightLength));
    }
}

