package com.icboluo.datastructure.linkedlist.doubl;

/**
 * @author lp
 */
public class DoubleLinkedListTest {
    public static void main(String[] args) {
        HeroNode2 heroNode21 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 heroNode22 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 heroNode23 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 heroNode24 = new HeroNode2(4, "林冲", "豹子头");
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
