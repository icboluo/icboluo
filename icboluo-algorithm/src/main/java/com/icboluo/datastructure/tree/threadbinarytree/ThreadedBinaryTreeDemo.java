package com.icboluo.datastructure.tree.threadbinarytree;

/**
 * 线索化二叉树
 *
 * @author icboluo
 * @date 2020/6/20 20:38
 */
 class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "jack");
        HeroNode node3 = new HeroNode(6, "sms");
        HeroNode node4 = new HeroNode(8, "mry");
        HeroNode node5 = new HeroNode(10, "k");
        HeroNode node6 = new HeroNode(14, "dm");

        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        ThreadBinaryTree t = new ThreadBinaryTree();
        t.setRoot(root);
        t.threadNodes();

        System.out.println(node5.getLeft());
        System.out.println(node5.getRight());
        t.threadList();
    }
}
