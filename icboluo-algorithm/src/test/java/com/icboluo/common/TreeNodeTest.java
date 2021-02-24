package com.icboluo.common;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TreeNodeTest {

    @Test
    public void test1() {
        Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        TreeNode treeNode = new TreeNode(arr);
        System.out.println("treeNode = " + treeNode);
        TreeNode.printTree(treeNode);
    }
}