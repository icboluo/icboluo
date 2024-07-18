package com.icboluo.leetcode.tree;

import com.icboluo.common.TreeNode;

import java.util.*;

/**
 * @author icboluo
 * @since 2021-11-17 23:55
 */
class N0104_0111二叉树最大最小深度 {
    public static void main(String[] args) {
        Integer[] arr = {1, 2, 3, 4, null, null, 5};
        TreeNode treeNode = new TreeNode(arr);
        var cla = new N0104_0111二叉树最大最小深度();
        int i = cla.maxDepth3(treeNode);
        System.out.println("i = " + i);
    }

    public int maxDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth1(root.left);
        int right = maxDepth1(root.right);
//         当三元左右均一样的时候，这里的比较大小可以使用Math.max来代替
        return left > right ? left + 1 : right + 1;
    }


    public int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        int max = 0;
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode pop = queue.poll();
                assert pop != null;
                if (pop.left != null) {
                    queue.add(pop.left);
                }
                if (pop.right != null) {
                    queue.add(pop.right);
                }
            }
            max++;
        }
        return max;
    }

    public int maxDepth3(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        Deque<Integer> depthStack = new ArrayDeque<>();
        stack.push(root);
        depthStack.push(1);
        int max = 1;
        while (!stack.isEmpty()) {
            int size = stack.size();
            for (int i = 0; i < size; i++) {
                TreeNode pop = stack.pop();
                int depthPop = depthStack.pop();
                if (pop.left == null && pop.right == null) {
//                    计算每个叶子节点的高度
                    max = Math.max(max, depthPop);
                }
//                push的时候从右到左，pop的时候就能从左到右，能保证每一次的pop顺序固定
                if (pop.right != null) {
                    stack.push(pop.right);
                    depthStack.push(depthPop + 1);
                }
                if (pop.left != null) {
                    stack.push(pop.left);
                    depthStack.push(depthPop + 1);
                }
            }
        }
        return max;
    }

    /**
     * N0111_二叉树最小深度
     *
     * @param root
     * @return
     */
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
