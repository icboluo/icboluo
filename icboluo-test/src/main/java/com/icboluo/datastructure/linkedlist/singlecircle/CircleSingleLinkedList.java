package com.icboluo.datastructure.linkedlist.singlecircle;

/**
 * @author icboluo
 */
public class CircleSingleLinkedList {
    private Boy first = new Boy(-1);

    /**
     * 构建
     *
     * @param nums 因为boy obj 只有一个no和next域，所以 nums相当于boy的collection
     */
    public void build(int nums) {
        if (nums < 1) {
            System.out.println("nums 值不正确");
            return;
        }
        Boy cur = null;
        for (int i = 1; i < nums; i++) {
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
                first.setNext(first);
                cur = first;
            } else {
                cur.setNext(boy);
                boy.setNext(first);
                cur = boy;
            }
        }
    }

    public void list() {
        if (first == null) {
            System.out.println("链表为null");
            return;
        }
        Boy cur = first;
        while (true) {
            System.out.printf("小孩的编号%d\n", cur.getNo());
            if (cur.getNext() == first) {
                break;
            }
            cur = cur.getNext();
        }
    }

    /**
     * 约瑟夫排序
     *  创建辅助指针end指向环形链表最后的节点（相对于first）
     * @param startNo  表示从第几个开始数数
     * @param countNum 表示数几下
     * @param nums     表示有多少boy在圈
     */
    public void order(int startNo, int countNum, int nums) {
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("不合法参数");
            return;
        }
        Boy end = first;
        //找到真正的end：end.next=first
        while (end.getNext() != first) {
            end = end.getNext();
        }
        //将end和first移动（将startNo归0）
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            end = end.getNext();
        }
        while (first != end) {
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                end = end.getNext();
            }
            System.out.printf("小孩%d出圈\n", first.getNo());
            first = first.getNext();
            end.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩编号为%d的\n", end.getNo());
    }
}
