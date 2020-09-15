package com.icboluo.datastructure.tree.binarysorttree;

/**
 * @author icboluo
 * @date 2020/7/27 01:10
 */
 class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9,2};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int value : arr) {
            binarySortTree.add(new Node(value));
        }
        //binarySortTree.infixOrder();

        binarySortTree.delNode(2);
        binarySortTree.infixOrder();
    }

}
