package com.icboluo.datastructure.tree.arrbinary;

import lombok.AllArgsConstructor;

/**
 * @author icboluo
 * @since 2020/6/16 12:16
 */
@AllArgsConstructor
 class ArrBinaryTree {
    /**
     * 存储节点
     */
    private int[] arr;

    public void preOrder() {
        this.preOrder(0);
    }

    /**
     * @param index 数组的下标
     */
    public void preOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
            return;
        }
        System.out.println(arr[index]);
        if (index * 2 + 1 < arr.length) {
            preOrder(2 * index + 1);
        }
        if (index * 2 + 2 < arr.length) {
            preOrder(2 * index + 2);
        }
    }
}
