package com.icboluo.datastructure.linkedlist.single;

import java.util.*;

/**
 * 链表：链表是以节点的方式存储的，每个节点包括data域和next域
 * 链表的各个节点在内存中不一定是连续存储的
 *
 * @author lp
 */
public class SingleLinkedListTest {
    public static void main(String[] args) {
        HeroNode heroNode1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode heroNode2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode heroNode3 = new HeroNode(3, "吴用", "智多星");
        HeroNode heroNode4 = new HeroNode(4, "林冲", "豹子头");
        SingleLinkedList list = new SingleLinkedList();
/*        list.add(heroNode1);
        list.add(heroNode3);
        list.add(heroNode2);
        list.add(heroNode4);    */

        list.addByOrder(heroNode1);
        list.addByOrder(heroNode3);
        list.addByOrder(heroNode3);
        list.addByOrder(heroNode2);
        list.addByOrder(heroNode4);

        HeroNode newHeroNode2 = new HeroNode(2, "猪", "猪猪猪");
        list.update(newHeroNode2);
        list.delete(5);

        System.out.println("有效的节点个数为：" + length(list.getHead()));
        HeroNode lastIndexNode = findLastIndexNode(list.getHead(), 2);
        System.out.println("lastIndexNode = " + lastIndexNode);
        System.out.println("*--*--*-*-*-*--*-*-**-*-*-*-*-*-*-*--**-*-*--*");

        list.list();
        System.out.println("*--*--*-*-*-*--*-*-**-*-*-*-*-*-*-*--**-*-*--*");
        reversePrint(list.getHead());
        System.out.println("*--*--*-*-*-*--*-*-**-*-*-*-*-*-*-*--**-*-*--*");
        reverseList(list.getHead());
        list.list();
    }

    /**
     * 从尾到头打印单链表
     * 方式1：反转打印：原单链表已经被反转，结果改变，不推荐
     * 方式2：利用栈数据结果，将各个节点压入栈中，用栈的先进后出原则打印
     */
    public static void reversePrint(HeroNode head) {
        if (head.next == null) {
            return;
        }
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

    /**
     * 反转
     *
     * @param head 头节点
     */
    public static void reverseList(HeroNode head) {
        if (head.next == null || head.next.next == null) {
            return;
        }
        HeroNode cur = head.next;
        //当前节点的下一个节点
        HeroNode next;
        HeroNode reverseHead = new HeroNode(0, "", "");
        //遍历原来的链表，每遍历一个节点，将其取出，并放在新的链表reverseHead 的最前面
        while (cur != null) {
            next = cur.next;
            cur.next = reverseHead.next;
            reverseHead.next = cur;
            cur = next;
        }
        head.next = reverseHead.next;
    }

    /**
     * 查找单链表中的倒数第k个节点
     *
     * @param head  头节点
     * @param index 倒数的索引数
     * @return 节点，如果没有找到，为null
     */
    private static HeroNode findLastIndexNode(HeroNode head, int index) {
        if (head == null) {
            return null;
        }
        int size = length(head);
        if (index <= 0 || index > size) {
            return null;
        }
        HeroNode current = head.next;
        for (int i = 0; i < size - index; i++) {
            current = current.next;

        }
        return current;
    }

    /**
     * @param head 链表的头节点
     * @return 有效节点的个数(不统计头节点)
     */
    private static int length(HeroNode head) {
        if (head.next == null) {
            return 0;
        }
        int length = 0;
        HeroNode current = head.next;
        while (current != null) {
            length++;
            current = current.next;
        }
        return length;
    }
}


