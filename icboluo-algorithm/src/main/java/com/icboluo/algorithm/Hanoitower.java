package com.icboluo.algorithm;

/**
 * 汉罗塔
 *
 * @author icboluo
 * @date 2020-08-04 12:30
 */
public class Hanoitower {
    public static void main(String[] args) {
        hanoitower(5, 'A', 'B', 'C');
    }

    /**
     * 将num个盘从a移动到c
     *
     * @param num 要移动盘的数量
     * @param a   a盘
     * @param b   b盘
     * @param c   c盘
     */
    public static void hanoitower(int num, char a, char b, char c) {
        if (num == 1) {
            System.out.println("第1个盘从" + a + "->" + c);
        } else {
//            将除了最后一个盘移动到b盘
            hanoitower(num - 1, a, c, b);
//            将最后一个盘移动到c盘
            System.out.println("第" + num + "个盘从" + a + "->" + c);
//            将b盘上的所有移动到c盘
            hanoitower(num - 1, b, a, c);
        }
    }
}
