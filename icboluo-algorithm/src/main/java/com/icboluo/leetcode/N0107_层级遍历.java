package com.icboluo.leetcode;

import com.icboluo.common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

/**
 * @author icboluo
 * @since 2022-03-25 18:49
 */
public class N0107_层级遍历 {
    public static void main(String[] args) {
        N0107_层级遍历 cla = new N0107_层级遍历();
        Integer[] arr = {3, 9, 20, null, null, 15, 7};
        TreeNode treeNode = new TreeNode(arr);
        List<List<Integer>> lists = cla.levelOrderBottom(treeNode);
        System.out.println("lists = " + lists);
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        order1(root);
        return ans.stream()
                .map(li -> li.stream().map(treeNode -> treeNode.val).collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    List<List<TreeNode>> ans;

    /**
     * 递归 why error
     *
     * @param root
     * @return
     */
    private void order1(TreeNode root) {
        ans = new ArrayList<>();
//         这个level可以理解为倒数第n个
        order1(root, 0);
    }

    private void order1(TreeNode root, int level) {
        if (root == null) {
            return;
        }
        if (level == ans.size()) {
//        正序解法    ans.add(new ArrayList<>());
            ans.add(0, new ArrayList<>());
        }
        order1(root.left, level + 1);
        order1(root.right, level + 1);
//        ans.get(level).add(root);
        ans.get(ans.size() - level - 1).add(root);
    }

    /**
     * 迭代
     *
     * @param root
     * @return
     */
    private void order2(TreeNode root) {
        ans = new ArrayList<>();
//        为什么这里要用队列呢，因为顺序比栈好嘛
        Queue<TreeNode> queue = new LinkedBlockingQueue<>();
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
