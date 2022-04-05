package com.icboluo.leetcode;

import com.icboluo.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author icboluo
 */
public class N0102_0103__0637层级遍历 {
    /**
     * 0101 层级遍历
     *
     * @param root root
     * @return ans
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
//            里面存储的是当前级别中的所有元素
            int count = queue.size();
            List<Integer> subList = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                TreeNode poll = queue.poll();
//                其实queue里面存储的值均！=null，用size去遍历，poll值不会为null
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
                subList.add(poll.val);
            }
            res.add(subList);
        }
        return res;
    }

    /**
     * 0103 锯齿遍历(之字形水平顺序
     *
     * @param root root
     * @return ans
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<TreeNode>> ans = new ArrayList<>();
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        linkedList.add(root);

        int row = 0;
        while (!linkedList.isEmpty()) {
            int size = linkedList.size();
            List<TreeNode> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
//                 栈和队列都是相等的，只是为了判断阅读顺序
                TreeNode pop = linkedList.pop();
                if (row % 2 == 0) {
                    list.add(pop);
                } else {
                    list.add(0, pop);
                }
//                永远是先左后右，避免了整体左右和单个节点左右难以处理
                if (pop.left != null) {
                    linkedList.addLast(pop.left);
                }
                if (pop.right != null) {
                    linkedList.addLast(pop.right);
                }
            }
            ans.add(list);
            row++;
        }
        return ans.stream()
                .map(li -> li.stream().map(tree -> tree.val).toList())
                .toList();
    }

    /**
     * 0637 层级遍历求平均值
     *
     * @param root root
     * @return 平均值
     */
    public List<Double> averageOfLevels(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<Double> ans = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
//             当不考虑顺序的时候，无需新加tempList去收集元素，只需要将queue深拷贝即可（考虑顺序不能这样处理，会造成难以理解处理
            List<Integer> tempList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                assert poll != null;
                tempList.add(poll.val);
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
            }
            double avg = tempList.stream()
                    .mapToInt(Integer::intValue)
                    .average().orElse(0);
            ans.add(avg);
        }
        return ans;
    }

    public static void main(String[] args) {
        N0102_0103__0637层级遍历 cla = new N0102_0103__0637层级遍历();
        Integer[] arr = {1, 2, 3, 4, null, null, 5};
        TreeNode treeNode = new TreeNode(arr);
        List<List<Integer>> lists = cla.zigzagLevelOrder(treeNode);
        System.out.println("lists = " + lists);
    }
}
