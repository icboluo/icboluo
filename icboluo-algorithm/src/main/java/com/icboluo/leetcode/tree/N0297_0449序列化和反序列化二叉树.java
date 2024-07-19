package com.icboluo.leetcode.tree;

import com.icboluo.common.TreeNode;

import java.util.*;

/**
 * @author icboluo
 * @since 2023-05-22 21:19
 */
class N0297_0449序列化和反序列化二叉树 {
    public static void main(String[] args) {
        var cla = new N0297_0449序列化和反序列化二叉树();
        TreeNode tree = new TreeNode(1, 2, 3, null, null, 4, 5);
        String sa1 = cla.serialize1(tree);
        System.out.println(sa1);
        // 题目本身没有做任何限制，只是让一组数据经过序列化和反序列化之后可以还原即可，层级遍历是一个易于想到的方案，但是不易实现；相反，前序遍历容易实现一点
        // 方法1使用的是前序遍历，序列化也使用的是前序遍历，是合理的
        cla.deserialize1(sa1).print();

        // 方法2使用的是层级遍历，但是没有层级遍历的序列化代码，需要重新排版
        String sa2 = cla.serialize2(tree);
        cla.deserialize2(sa2).print();
    }

    String spliter = ",";

    String nullEle = "null";

    // optimize Encodes a tree to a single string.
    // 序列化的结果并不是标准的完全二叉树层级遍历结果，而是一个前序遍历结果
    // 同样，反序列化的过程也是针对于前序遍历的结果进行处理，此过程非常难以理解
    public String serialize1(TreeNode root) {
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
    public TreeNode deserialize1(String data) {
        // 对于字符串是难以操作的，转换为可操作列表
        Deque<Integer> queue = buildDeque(data);
        return buildTree1(queue);
    }

    // dfs的书写是简单的，理解难度挺简单
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

    public String serialize2(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        preOrder(root, sb);
        return sb.toString();
    }

    public TreeNode deserialize2(String data) {
        Deque<Integer> queue = buildDeque(data);
        return buildTree2(new ArrayList<>(queue));
    }

    private TreeNode buildTree2(List<Integer> list) {
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

    private Deque<Integer> buildDeque(String data) {
        Deque<Integer> queue = new LinkedList<>();
        for (String val : data.split(spliter)) {
            if (val.equals(nullEle)) {
                queue.offer(null);
            } else {
                queue.offer(Integer.parseInt(val));
            }
        }
        return queue;
    }


    // FIXME
    // N0449 Encodes a tree to a single string.  ----------------------------------------------------------
    public String serialize(TreeNode root) {
        final StringBuilder sb = new StringBuilder();
        ser(root, sb);
        return sb.toString();
    }

    private void ser(TreeNode root, StringBuilder sb) {
        // optimize 二叉搜索树不需要确定哪一个节点是最终节点
        if (root == null) {
            return;
        }
        sb.append(root.val).append(",");
        ser(root.left, sb);
        ser(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        final String[] split = data.split(",");
        final Queue<String> queue = new LinkedList<>(Arrays.asList(split));
        return des(queue, Integer.MAX_VALUE, Integer.MIN_VALUE);
    }

    private TreeNode des(Queue<String> queue, int max, int min) {
        if (queue.isEmpty()) {
            return null;
        }
        final int poll = Integer.parseInt(queue.poll());
        // 在这里可以判断二叉搜索树的叶子节点；只有当节点比根节点的值低的时候，才能添加到树的左子节点
        // 如果 val>supper ，则不要将其添加到左子节点  完整的写法是这样的poll > max || poll < min，因为是前序遍历，所以总是先遇到左节点，如果左节点不存在说明当前节点已经g了
        if (poll > max || poll < min) {
            return null;
        }
        final TreeNode cur = new TreeNode(poll);
        cur.left = des(queue, min, poll);
        cur.right = des(queue, poll, max);
        return cur;
    }

    // N0255 验证二叉搜索树中的预排序序列 ---------------------------------------------------------------------
    int start;

    public boolean verifyPreorder(int[] preorder) {
        helper(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return start == preorder.length;
    }

    public void helper(int[] preorder, int lower, int upper) {
        if (start == preorder.length) {
            return;
        }
        int val = preorder[start];
        if (val < lower || val > upper) {
            return;
        }
        start++;
        helper(preorder, lower, val);
        helper(preorder, val, upper);
    }
}

