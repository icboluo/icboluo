package com.icboluo.leetcode.tree;

import com.icboluo.common.TreeNode;

import java.util.*;

/**
 * @author icboluo
 * @since 2023-05-22 21:19
 */
class N0297_序列化和反序列化二叉树 {
//    FIXME
    public static void main(String[] args) {
        var cla = new N0297_序列化和反序列化二叉树();
        TreeNode tree = cla.deserialize("1,2,3,null,null,4,5");
        tree.print();
        System.out.println(cla.serialize(tree));
    }

    String spliter = ",";

    String nullEle = "null";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        // 这个也就是层级遍历，可以将层级遍历的代码迁移过来
        StringBuilder sb = new StringBuilder();
        preOrder(root, sb);
        return sb.toString();
    }

    private void preOrder(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append(nullEle).append(spliter);
            return;
        }
        // 前序遍历
        sb.append(root.val).append(spliter);
        preOrder(root.left, sb);
        preOrder(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        // 对于字符串是难以操作的，转换为可操作列表
        Deque<Integer> queue = new LinkedList<>();
        for (String val : data.split(spliter)) {
            if (val.equals(nullEle)) {
                queue.offer(null);
            } else {
                queue.offer(Integer.parseInt(val));
            }
        }
        return buildTree1(queue);
//        return buildTree3(queue.toArray(Integer[]::new), 0);
    }

    // dfs的书写是简单的，理解难度挺简单
    // TODO 将 TreeNode 里面的dfs也移动过来测试, 应该提取一个treeUtil
    private TreeNode buildTree1(Queue<Integer> queue) {
        Integer first = queue.poll();
        if (first == null) {
            return null;
        }
        TreeNode root = new TreeNode(first);
        root.left = buildTree1(queue);
        root.right = buildTree1(queue);
        return root;
    }
    // FIXME ERROR

    private TreeNode buildTree2(List<Integer> list) {
        if (list.get(0) == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(list.get(0));
        queue.add(root);
        int i = 1;
        while (i < list.size()) {
            // 奇怪的bfs，其实，队列里面的数值是倍数扩展的，所以队列头的left为下一行的数据，这里需要一点点证明，但是很麻烦
            TreeNode parent = queue.poll();
            assert parent != null;
            if (list.get(i) != null) {
                TreeNode left = new TreeNode(list.get(i));
                parent.left = left;
                queue.add(left);
            }
            // null也是元素，指针也必须移动，不要把++逻辑写到if中
            i++;
            if (list.get(i) != null) {
                TreeNode right = new TreeNode(list.get(i));
                parent.right = right;
                queue.add(right);
            }
            i++;
        }
        return root;
    }

    // FIXME ERROR
    private TreeNode buildTree3(Integer[] arr, int index) {
        if (index >= arr.length || arr[index] == null) {
            return null;
        }
        TreeNode cur = new TreeNode(arr[index]);
        cur.left = buildTree3(arr, index * 2 + 1);
        cur.right = buildTree3(arr, index * 2 + 2);
        return cur;
    }
}
