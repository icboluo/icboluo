package com.icboluo.datastructure.tree.binary;

/**
 * @author icboluo
 * @since 2020/6/14 16:55
 */
class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");
        binaryTree.setRoot(root);
        root.setLeft(node2);
        root.setRight(node3);
        node3.setLeft(node5);
        node3.setRight(node4);

        binaryTree.preOrder();
        binaryTree.infixOrder();
        binaryTree.postOrder();

        HeroNode heroNode = binaryTree.preOrderSearch(5);
        if (heroNode != null) {
            System.out.println(heroNode);
        } else {
            System.out.println("没找到");
        }
        binaryTree.delete(3);
        binaryTree.preOrder();


    }
}
