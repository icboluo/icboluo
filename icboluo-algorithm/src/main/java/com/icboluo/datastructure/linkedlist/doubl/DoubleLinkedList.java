package com.icboluo.datastructure.linkedlist.doubl;

import lombok.Getter;

/**
 * @author lp
 * 双向链表的（添加元素到链表结尾），（更新链表中的元素）做法是和单向链表相同的
 */
class DoubleLinkedList {
    @Getter
    private final HeroNode2 head = new HeroNode2(0, "");


    /**
     * 遍历和单向链表一样
     */
    public void list() {
        if (head.next == null) {
            System.out.println("链表为null");
            return;
        }
        HeroNode2 temp = head.next;
        //判断链表是否到最后
        while (temp != null) {
            System.out.println(temp);
            //将next后移
            temp = temp.next;
        }
    }

    /**
     * 添加一个节点到双向链表的最后
     *
     * @param heroNode2 头节点
     */
    public void add(HeroNode2 heroNode2) {
        HeroNode2 temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = heroNode2;
        heroNode2.pre = temp;
    }

    /**
     * 双向链表可以自我删除
     *
     * @param no 要删除链表的编号
     */
    public void delete(int no) {
        if (head.next == null) {
            System.out.println("链表为null，无法删除");
            return;
        }
        HeroNode2 temp = this.head.next;
        //是否找到待删除节点
        boolean b = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == no) {
                b = true;
                break;
            }
            temp = temp.next;
        }
        if (b) {
            temp.pre.next = temp.next;
            //如果要删除的节点不是最后一个节点
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.printf("要删除的节点 %d 不存在\n", no);
        }
    }

}
