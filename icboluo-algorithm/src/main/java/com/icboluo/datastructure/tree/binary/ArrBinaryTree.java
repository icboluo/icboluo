package com.icboluo.datastructure.tree.binary;

import lombok.AllArgsConstructor;

/**
 * 顺序存储二叉树，顺序存储二叉树针对的是完全二叉树，其实二叉树的层级遍历，是根据2*n的公式来的
 *
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

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.preOrder();
    }
}
