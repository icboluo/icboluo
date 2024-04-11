package com.icboluo.datastructure.linkedlist.doubl;

/**
 * @author lp
 */
class Test {
    public static void main(String[] args) {
        HeroNode heroNode21 = new HeroNode(1, "宋江");
        HeroNode heroNode22 = new HeroNode(2, "卢俊义");
        HeroNode heroNode23 = new HeroNode(3, "吴用");
        HeroNode heroNode24 = new HeroNode(4, "林冲");
        DoubleLinkedList list = new DoubleLinkedList();
        list.add(heroNode21);
        list.add(heroNode22);
        list.add(heroNode23);
        list.add(heroNode24);
        list.list();
        list.delete(3);
        System.out.println("-*****************-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
        list.list();
    }
}
