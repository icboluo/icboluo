package com.icboluo.datastructure.linkedlist.singlecircle;

/**
 * 单向环形链表解决约瑟夫问题：
 * 节点围成一圈，挨着报数，每次报数到n，（终止，出列），继续报数，
 * 直至环形链表null，排列出的新的集合
 *
 * @author icboluo
 */
public class JosephuTest {

    public static void main(String[] args) {

        CircleSingleLinkedList list = new CircleSingleLinkedList();
        list.build(5);
        list.list();

        list.order(1,2,5);
    }
}
