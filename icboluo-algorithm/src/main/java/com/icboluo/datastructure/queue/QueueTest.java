package com.icboluo.datastructure.queue;

import java.util.Scanner;

/**
 * 队列：先入先出原则
 * 队列有2种实现方式：1.数组，2.链表
 * 队列下面分为2种：一种是数组队列，一种是循环数组队列
 *
 * @author lp
 */
class QueueTest {
    /**
     * 因为在test中无法输入，用psvm来写test
     */
    public static void main(String[] args) {
        int i = 1;
        switch (i) {
            case 1:
                arrayQueueTest();
                break;
            case 2:
                circleQueueTest();
                break;
            default:
                break;
        }
    }


    /**
     * 这种数组模拟队列，实际上并没有取出任何的元素，只是指针移动了
     * 数组使用一次就不能再次使用了，没有达到复用的效果
     */
    private static void arrayQueueTest() {
        ArrayQueue queue = new ArrayQueue(3);
        String key;
        Scanner sc = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("offer：添加元素到队列，不抛异常");
            System.out.println("element：显示第一个元素，抛异常");
            System.out.println("exit：退出程序");
            System.out.println("show：显示队列");
            System.out.println("remove：取出第一个元素");
            key = sc.next();
            switch (key) {
                case "offer":
                    System.out.println("请输入一个数");
                    int value = sc.nextInt();
                    queue.offer(value);
                    break;
                case "element":
                    int element = queue.element();
                    System.out.println("取出的数据为：" + element);
                    break;
                case "exit":
                    sc.close();
                    loop = false;
                    break;
                case "show":
                    queue.print();
                    break;
                case "remove":
                    queue.remove();
                default:
                    break;
            }
        }
    }

    private static void circleQueueTest() {
        //其队列的最大有效长度是3
        CircleQueue queue = new CircleQueue(4);
        String key;
        Scanner sc = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("offer：添加元素到队列，不抛异常");
            System.out.println("element：显示第一个元素，抛异常");
            System.out.println("exit：退出程序");
            System.out.println("show：显示队列");
            System.out.println("remove：取出第一个元素");
            key = sc.next();
            switch (key) {
                case "offer":
                    System.out.println("请输入一个数");
                    int value = sc.nextInt();
                    queue.offer(value);
                    break;
                case "element":
                    int element = queue.element();
                    System.out.println("取出的数据为：" + element);
                    break;
                case "exit":
                    sc.close();
                    loop = false;
                    break;
                case "show":
                    queue.print();
                    break;
                case "remove":
                    queue.remove();
                default:
                    break;
            }
        }
    }
}




