package com.icboluo.datastructure.linkedlist.single;

import lombok.ToString;

/**
 * 初始节点
 */
@ToString
class HeroNode {
    public int no;
    public String name;
    public HeroNode next;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }
}
