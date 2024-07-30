package com.icboluo.leetcode.tree;

import com.icboluo.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author icboluo
 * @since 2024-07-31 上午1:56
 */
class N0623_给树增加一行 {
    public static void main(String[] args) {
        N0623_给树增加一行 cla = new N0623_给树增加一行();
        cla.addOneRow(new TreeNode(4, 2, 6, 3, 1, 5), 1, 2).print();
    }

    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (depth == 1) {
            final TreeNode treeNode = new TreeNode(val);
            treeNode.left = root;
            return treeNode;
        }
//        return addOneRow1(root, val, depth);
        return addOneRow2(root, val, depth, 2);
    }

    public TreeNode addOneRow1(TreeNode root, int val, int depth) {
        final Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        for (int i = 0; i < depth - 2; i++) {
            final int size = queue.size();
            for (int j = 0; j < size; j++) {
                final TreeNode poll = queue.poll();
                assert poll != null;
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
            }
        }

        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            final TreeNode tempLeft = new TreeNode(val);
            tempLeft.left = poll.left;
            poll.left = tempLeft;

            final TreeNode tempRight = new TreeNode(val);
            tempRight.right = poll.right;
            poll.right = tempRight;
        }
        return root;
    }

    public TreeNode addOneRow2(TreeNode root, int val, int depth, int level) {
        if (root == null) {
            return null;
        }
        if (level == depth) {
            TreeNode left = root.left;
            TreeNode right = root.right;
            root.left = new TreeNode(val);
            root.right = new TreeNode(val);
            root.left.left = left;
            root.right.right = right;
            return root;
        }

        root.left = addOneRow2(root.left, val, depth, level + 1);
        root.right = addOneRow2(root.right, val, depth, level + 1);
        return root;
    }
}
