package com.icboluo.leetcode.tree.层级遍历;

import com.icboluo.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author icboluo
 * @since 2022-03-25 18:49
 */
class N0107_倒序层级遍历 {
    public static void main(String[] args) {
        var cla = new N0107_倒序层级遍历();
        Integer[] arr = {3, 9, 20, null, null, 15, 7};
        TreeNode treeNode = new TreeNode(arr);
        List<List<Integer>> lists = cla.levelOrderBottom(treeNode);
        System.out.println("lists = " + lists);
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        ans = new ArrayList<>();
        //         这个level可以理解为倒数第n个
        order1(root, 0);
        return ans.stream()
                .map(li -> li.stream().map(treeNode -> treeNode.val).toList())
                .toList();
    }

    List<List<TreeNode>> ans;

    /**
     * 递归
     *
     * @param root
     * @param level
     */
    private void order1(TreeNode root, int level) {
        if (root == null) {
            return;
        }
        if (level == ans.size()) {
            ans.add(0, new ArrayList<>());
        }
        order1(root.left, level + 1);
        order1(root.right, level + 1);
        ans.get(ans.size() - level - 1).add(root);
    }

    /**
     * 正序解法
     *
     * @param root
     * @param level
     */
    private void order3(TreeNode root, int level) {
        if (root == null) {
            return;
        }
        if (level == ans.size()) {
            ans.add(new ArrayList<>());
        }
        order3(root.left, level + 1);
        order3(root.right, level + 1);
        ans.get(level).add(root);
    }

    /**
     * 迭代
     *
     * @param root
     * @return
     */
    private void order2(TreeNode root) {
        ans = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<TreeNode> list = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                assert poll != null;
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
                list.add(poll);
            }
            ans.add(0, list);
        }
    }
}
