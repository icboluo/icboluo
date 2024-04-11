package com.icboluo.datastructure.linkedlist.single;

import lombok.Getter;

class SingleLinkedList {
    /**
     * 初始化头节点，头节点不动，不存放具体的值
     */
    @Getter
    private final HeroNode head = new HeroNode(0, "");

    /**
     * 找到当前链表的最后节点，将最后节点的next域指向新的节点
     *
     * @param heroNode 链表中新的数据
     */
    public void add(HeroNode heroNode) {
        HeroNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = heroNode;
    }

    /**
     * 根据编号按顺序增加
     *
     * @param heroNode 增加的全部信息
     */
    public void addByOrder(HeroNode heroNode) {
        HeroNode temp = head;
        //添加的hero编号是否存在
        boolean b = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            //找到添加位置
            if (temp.next.no > heroNode.no) {
                break;
            }
            if (temp.next.no == heroNode.no) {
                b = true;
                break;
            }
            temp = temp.next;
        }
        if (b) {
            System.out.printf("待插入数据no：%d 已经存在\n", heroNode.no);
        } else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    /**
     * @param heroNode 根据节点编号修改内容
     */
    public void update(HeroNode heroNode) {
        if (head.next == null) {
            System.out.println("链表为null");
            return;
        }
        HeroNode temp = head.next;
        //是否找到
        boolean b = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == heroNode.no) {
                b = true;
                break;
            }
            temp = temp.next;
        }
        if (b) {
            temp.name = heroNode.name;
        } else {
            System.out.printf("没有找到编号 %d 的节点\n", +heroNode.no);
        }
    }

    public void delete(int no) {
        HeroNode temp = this.head;
        //是否找到待删除节点
        boolean b = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == no) {
                b = true;
                break;
            }
            temp = temp.next;
        }
        if (b) {
            temp.next = temp.next.next;
        } else {
            System.out.printf("要删除的节点 %d 不存在\n", no);
        }
    }

    /**
     * 遍历的时候，next节点包含链表之后的信息，所以会遍历出很多的next数据
     */
    public void list() {
        if (head.next == null) {
            System.out.println("链表为null");
            return;
        }
        HeroNode temp = head.next;
        //判断链表是否到最后
        while (temp != null) {
            System.out.println(temp);
            //将next后移
            temp = temp.next;
        }
    }
}
