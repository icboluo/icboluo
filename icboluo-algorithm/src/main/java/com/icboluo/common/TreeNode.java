package com.icboluo.common;

import lombok.NoArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 二叉树树节点，并不对树进行方法抽取，只做算法使用
 *
 * @author icboluo
 * @since 2020-09-27 19:12
 */
@NoArgsConstructor
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    /**
     * 二叉树的构造方法
     *
     * @param arr 层级遍历的数组
     */
    public TreeNode(Integer... arr) {
        TreeNode treeNode;
        try {
            // 完全二叉树的构造方法
            treeNode = getInstance1(arr, 0);
        } catch (Exception e) {
            treeNode = getInstance2(arr);
        }
        if (treeNode == null) {
            return;
        }
        this.val = treeNode.val;
        this.left = treeNode.left;
        this.right = treeNode.right;
    }

    /**
     * 生成完全二叉树
     *
     * @param arr 数组
     * @return 完全二叉树
     */
    public static TreeNode inCompleteTree(Integer... arr) {
        return getInstance1(arr, 0);
    }

    /**
     * 生成非完全二叉树
     *
     * @param arr 数组
     * @return 非完全二叉树
     */
    public static TreeNode outCompleteTree(Integer... arr) {
        return getInstance2(arr);
    }

    public TreeNode(TreeNode root) {
        this.val = root.val;
        this.left = root.left == null ? null : new TreeNode(root.left);
        this.right = root.right == null ? null : new TreeNode(root.right);
    }

    public TreeNode del(int key) {
        return del(this, key);
    }

    /**
     * 二叉查找树的删除
     *
     * @param root 根节点
     * @param key  要删除的val
     * @return 新树
     */
    private TreeNode del(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val == key) {
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            // 取右子树的最小节点|||||||左子树的最大节点
            TreeNode rightMin = root.right;
            while (rightMin.left != null) {
                rightMin = rightMin.left;
            }
            root.right = del(root.right, rightMin.val);
            rightMin.left = root.left;
            rightMin.right = root.right;
            return rightMin;
        } else if (root.val > key) {
            root.left = del(root.left, key);
        } else {
            root.right = del(root.right, key);
        }
        return root;
    }

    private static TreeNode getInstance1(Integer[] arr, int index) {
        if (index >= arr.length || arr[index] == null) {
            return null;
        }
        TreeNode cur = new TreeNode(arr[index]);
        cur.left = getInstance1(arr, index * 2 + 1);
        cur.right = getInstance1(arr, index * 2 + 2);
        return cur;
    }

    private static TreeNode getInstance2(Integer[] arr) {
        TreeNode tempRoot = new TreeNode(arr[0]);
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(tempRoot);
        int idx = 0;
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            if (++idx < arr.length && arr[idx] != null) {
                poll.left = new TreeNode(arr[idx]);
                queue.add(poll.left);
            }
            if (++idx < arr.length && arr[idx] != null) {
                poll.right = new TreeNode(arr[idx]);
                queue.add(poll.right);
            }
        }
        return tempRoot;
    }

    public TreeNode print() {
        String[][] arr = toArr(this);
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
            for (int j = 0; j < arr[i].length; j++) {
                if (i < arr.length - 1) {
                    if (" |-".equals(arr[i][j])) {
                        System.out.print(" ↓ ");
                    } else if ("-| ".equals(arr[i][j])) {
                        System.out.print(" ↓ ");
                    } else {
                        System.out.print("   ");
                    }
                }
            }
            System.out.println();
        }
        return this;
    }

    private String[][] toArr(TreeNode root) {
        Node.all = new ArrayList<>();
        Node.startCol = 0;
        Node node = new Node(root, 0);
        int height = height();
        int count = count();
        String[][] res = new String[height][count];
        for (String[] row : res) {
            Arrays.fill(row, "   ");
        }
        Map<Integer, List<Node>> rowMap = Node.all.stream().collect(Collectors.groupingBy(n -> n.row));

        for (int i = 0; i < height - 1; i++) {
            List<Node> curList = rowMap.get(i);
            for (Node cur : curList) {
                if (cur.left != null) {
                    for (int j = cur.left.col + 1; j <= cur.col - 1; j++) {
                        res[i][j] = "---";
                    }
                    res[i][cur.left.col] = " |-";
                }
                if (cur.right != null) {
                    for (int j = cur.col + 1; j <= cur.right.col - 1; j++) {
                        res[i][j] = "---";
                    }
                    res[i][cur.right.col] = "-| ";
                }
                res[i][cur.col] = cur.print;
            }
        }
        List<Node> last = rowMap.get(height - 1);
        for (Node cur : last) {
            res[height - 1][cur.col] = cur.print;
        }
        return res;
    }

    static class Node {
        TreeNode.Node left;

        TreeNode.Node right;

        int val;

        int row;

        int col;

        String print;

        static int startCol;

        static List<TreeNode.Node> all = new ArrayList<>();

        public Node(TreeNode root, int row) {
            this.val = root.val;
            this.row = row;
            this.left = root.left == null ? null : new Node(root.left, row + 1);
            // 中序遍历
            this.col = startCol++;
            this.right = root.right == null ? null : new Node(root.right, row + 1);
            this.setPrint();
            all.add(this);
        }

        private void setPrint() {
            String item;
            // 只有一位数
            if (this.val < 10) {
                item = " " + this.val + " ";
            } else {
                item = "   " + this.val;
                item = item.substring(item.length() - 3);
            }
            this.print = item;
        }
    }

    /**
     * 求元素个数
     */
    public int count() {
        return count(this);
    }

    private int count(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return count(root.left) + count(root.right) + 1;
    }

    /**
     * 求二叉树最大高度
     *
     * @return 最大高度
     */
    public int height() {
        return height(this);
    }


    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(height(root.left), height(root.right)) + 1;
    }

    /**
     * 二叉树的最小深度
     *
     * @return 最小深度
     */
    public int minDepth() {
        return minDepth(this);
    }

    private int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        return root.left == null || root.right == null ?
                left + right + 1 : Math.min(left, right) + 1;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TreeNode treeNode = (TreeNode) o;
        return val == treeNode.val;
    }

    @Override
    public int hashCode() {
        return Objects.hash(val);
    }
}
