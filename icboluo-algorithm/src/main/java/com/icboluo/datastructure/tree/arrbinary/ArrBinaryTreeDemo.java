package com.icboluo.datastructure.tree.arrbinary;

/**
 *
 *
 * @author icboluo
 * @since 2020/6/15 12:53
 */
 class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.preOrder();
    }
}
