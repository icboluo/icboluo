package com.icboluo.leetcode.after_0000;

import com.icboluo.common.TreeNode;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

/**
 * @author icboluo
 */
class N0102_0103__0637层级遍历 {
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
     * 0103 锯齿遍历（reverse TODO ERROR
     *
     * @param root root
     * @return ans
     */
    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<TreeNode>> ans = new ArrayList<>();
        LinkedBlockingQueue<TreeNode> queue = new LinkedBlockingQueue<>();
        queue.add(root);

        int row = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<TreeNode> temp = new ArrayList<>(queue);
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
            }
            if (row % 2 == 1) {
                ans.add(temp.stream().sorted((a, b) -> 1).collect(Collectors.toList()));
            } else {
                ans.add(temp);
            }
            row++;
        }
        return ans.stream()
                .map(li -> li.stream().map(tree -> tree.val).toList())
                .toList();
    }

    /**
     * 0103 锯齿遍历（reverse
     *
     * @param root root
     * @return ans
     */
    public List<List<Integer>> zigzagLevelOrder3(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        LinkedBlockingQueue<TreeNode> queue = new LinkedBlockingQueue<>();
        queue.add(root);
        int count = 0;
        List<List<TreeNode>> ans = new ArrayList<>();
        while (!queue.isEmpty()) {
            count++;
            if (count % 2 == 0) {
                queue = reverse(queue);
            }
            ans.add(new ArrayList<>(queue));
            queue = reverse(queue);
            for (TreeNode next : queue) {
                if (next.left != null) {
                    queue.add(next.left);
                }
                if (next.right != null) {
                    queue.add(next.right);
                }
                queue.remove(next);
            }
        }
        return ans.stream()
                .map(li -> li.stream().map(tree -> tree.val).toList())
                .collect(Collectors.toList());
    }

    private LinkedBlockingQueue<TreeNode> reverse(LinkedBlockingQueue<TreeNode> queue) {
        LinkedBlockingQueue<TreeNode> res = new LinkedBlockingQueue<>();
        ArrayList<TreeNode> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            list.add(queue.poll());
        }
        for (int i = list.size() - 1; i >= 0; i--) {
            res.offer(list.get(i));
        }
        return res;
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

        Integer[] arr103 = {3, 9, 20, null, null, 15, 7};
        TreeNode treeNode103 = new TreeNode(arr103);
        List<List<Integer>> lists1 = cla.zigzagLevelOrder2(treeNode103);
        System.out.println("lists1 = " + lists1);
    }
}
