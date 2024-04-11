package com.icboluo.datastructure.linkedlist.doubl;

/**
 * 初始节点
 */

class HeroNode {
    public int no;
    public String name;
    public HeroNode next;
    public HeroNode pre;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + +'\'' +
                '}';
    }
}
