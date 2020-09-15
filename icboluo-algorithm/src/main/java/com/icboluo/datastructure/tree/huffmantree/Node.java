package com.icboluo.datastructure.tree.huffmantree;

/**
 * @author icboluo
 */
 class Node implements Comparable<Node> {
    /**
     * 权值
     */
    int value;
    /**
     * 左子结点
     */
    Node left;
    /**
     * 右子结点
     */
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}
