package com.icboluo.leetcode.after_0100;

import com.icboluo.common.TreeNode;

import java.util.*;

/**
 * @author icboluo
 * @since 2021-11-17 23:55
 */
class N0104_二叉树最大深度 {

    public static void main(String[] args) {
        Integer[] arr = {1, 2, 3, 4, null, null, 5};
        TreeNode treeNode = new TreeNode(arr);
        var cla = new N0104_二叉树最大深度();
        int i = cla.maxDepth4(treeNode);
        System.out.println("i = " + i);
    }

    public int maxDepth(TreeNode root) {
        return maxDepth4(root);
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

    /**
     * 错误解法：
     *
     * @param root
     * @return
     */
    public int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Stack<TreeNode> stack = new Stack<>();
        int max = 0;
        stack.push(root);
        while (!stack.isEmpty()) {
            int size = stack.size();
//            想法不错，把每一层都迭代一次
            for (int i = 0; i < size; i++) {
//                每次刚push进去，下次循环立马pop出来没什么用，首部加，尾部取就可以了
                TreeNode pop = stack.pop();
                if (pop.left != null) {
                    stack.push(pop.left);
                }
                if (pop.right != null) {
                    stack.push(pop.right);
                }
            }
            max++;
        }
        return max;
    }

    /**
     * TODO 用的是addLast方法，没必要使用栈，应该使用队列linkedList
     *
     * @param root
     * @return
     */
    public int maxDepth3(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        int max = 0;
        stack.push(root);
        while (!stack.isEmpty()) {
            int size = stack.size();
            for (int i = 0; i < size; i++) {
                TreeNode pop = stack.pop();
                if (pop.left != null) {
                    stack.addLast(pop.left);
                }
                if (pop.right != null) {
                    stack.addLast(pop.right);
                }
            }
            max++;
        }
        return max;
    }

    public int maxDepth4(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> depthStack = new Stack<>();
        stack.push(root);
        depthStack.push(1);
        int max = 1;
        while (!stack.isEmpty()) {
            int size = stack.size();
            for (int i = 0; i < size; i++) {
                TreeNode pop = stack.pop();
                Integer depthPop = depthStack.pop();
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
}
