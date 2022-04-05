package com.icboluo.datastructure.tree.huffmancode;

/**
 * @author icboluo
 * @since 2020/7/23 12:24
 */
 class Node implements Comparable<Node> {
    Byte data;
    /**
     * 权值：数据出现的次数
     */
    int weight;
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

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
