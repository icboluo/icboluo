package com.icboluo.common;

import org.junit.jupiter.api.Test;

class TreeNodeTest {

    @Test
    void print() {
        TreeNode treeNode = new TreeNode(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19);
        treeNode.print();
    }

    @Test
    void delete() {
        TreeNode root = TreeNode.outCompleteTree(8, 4, 10, 2, 6, 9, 11, 1, 3, 5).print();
        root.del(4).print();
    }
}
