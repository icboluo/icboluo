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
     * 完全二叉树的构造方法
     *
     * @param arr 层级遍历的数组
     */
    public TreeNode(Integer... arr) {
        TreeNode treeNode = getInstance(arr, 0);
        if (treeNode == null) {
            return;
        }
        this.val = treeNode.val;
        this.left = treeNode.left;
        this.right = treeNode.right;
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
            TreeNode rightMin = root;
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

    /**
     * TODO it is error
     *
     * @param arr 中序遍历数组
     * @return 树的根节点
     */
    public static TreeNode getLevelOrderInstance(Integer[] arr) {
        if (arr == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(arr[0]);
        queue.offer(root);
        int index = 1;
        while (index < arr.length - 1) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (poll == null) {
                    continue;
                }
//                如果节点是值为null，代表left or right 为null，不需要构造空子树
                if (arr[index] != null) {
                    poll.left = new TreeNode(arr[index]);
                    queue.offer(poll.left);
                }
                if (arr[index + 1] != null) {
                    poll.right = new TreeNode(arr[index + 1]);
                    queue.offer(poll.right);
                }
                index += 2;

            }
        }
        return root;
    }

    /**
     * 收集节点val
     *
     * @param list 节点集合
     * @return val集合
     */
    public static List<Integer> collectNodeVal(List<TreeNode> list) {
        return list.stream().map(treeNode -> treeNode.val).toList();
    }

    private TreeNode getInstance(Integer[] arr, int index) {
        if (index >= arr.length || arr[index] == null) {
            return null;
        }
        TreeNode cur = new TreeNode(arr[index]);
        cur.left = getInstance(arr, index * 2 + 1);
        cur.right = getInstance(arr, index * 2 + 2);
        return cur;
    }

    public void print() {
        String[][] arr = toArr(this);
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
            for (int j = 0; j < arr[i].length; j++) {
                if (i < arr.length - 1) {
                    if (" |-".equals(arr[i][j])) {
                        System.out.print(" | ");
                    } else if ("-| ".equals(arr[i][j])) {
                        System.out.print(" | ");
                    } else {
                        System.out.print("   ");
                    }
                }
            }
            System.out.println();
        }
    }

    private String[][] toArr(TreeNode root) {
        sortIdx = 0;
        int height = height();
        int count = count();
        Node[][] nodeArr = new Node[height][count];
        String[][] arr = new String[height][count];
        for (String[] row : arr) {
            Arrays.fill(row, "   ");
        }
        buildArr(root, 0, nodeArr);
        Map<Integer, List<Node>> weiZhiMap = eleWeiZhi(nodeArr);
        for (int i = 0; i < height - 1; i++) {
            List<Node> curList = weiZhiMap.get(i);
            List<Node> nextList = weiZhiMap.get(i + 1);
            Map<Integer, Node> nextValMap = nextList.stream().collect(Collectors.toMap(node -> node.val, node -> node));
            for (Node cur : curList) {
                if (cur.left != -1) {
                    Node left = nextValMap.get(cur.left);
                    for (int j = left.idx + 1; j <= cur.idx - 1; j++) {
                        arr[i][j] = "---";
                    }
                    arr[i][left.idx] = " |-";
                }
                if (cur.right != -1) {
                    Node right = nextValMap.get(cur.right);
                    for (int j = cur.idx + 1; j <= right.idx - 1; j++) {
                        arr[i][j] = "---";
                    }
                    arr[i][right.idx] = "-| ";
                }
                arr[i][cur.idx] = cur.print;
            }
        }
        List<Node> last = weiZhiMap.get(height - 1);
        for (Node cur : last) {
            arr[height - 1][cur.idx] = cur.print;
        }
        return arr;
    }

    private Map<Integer, List<Node>> eleWeiZhi(Node[][] arr) {
        Map<Integer, List<Node>> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] != null) {
                    map.computeIfAbsent(i, key -> new ArrayList<>()).add(arr[i][j]);
                }
            }
        }
        return map;
    }

    private static int sortIdx = 0;

    private void buildArr(TreeNode root, int level, Node[][] arr) {
        if (root == null) {
            return;
        }
        buildArr(root.left, level + 1, arr);
        arr[level][sortIdx++] = new Node(root, sortIdx);
        buildArr(root.right, level + 1, arr);
    }

    class Node {
        int val;
        int left = -1;
        int right = -1;
        String print;
        int idx;

        public Node(TreeNode root, int sortIdx) {
            this.val = root.val;
            this.idx = sortIdx - 1;
            if (root.left != null) {
                left = root.left.val;
            }
            if (root.right != null) {
                right = root.right.val;
            }
            String item;
            // 只有一位数
            if (root.val < 10) {
                item = " " + root.val + " ";
            } else {
                item = "   " + root.val;
                item = item.substring(item.length() - 3);
            }
            this.print = item;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    '}';
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
