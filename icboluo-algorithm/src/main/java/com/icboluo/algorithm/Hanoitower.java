package com.icboluo.algorithm;

/**
 * 汉罗塔
 *
 * @author icboluo
 * @date 2020-08-04 12:30
 */
public class Hanoitower {
    public static void main(String[] args) {
        hanoitower(5,'A','B','C');
    }
    /**
     * 从a到c
     *
     * @param num
     * @param a
     * @param b
     * @param c
     */
    public static void hanoitower(int num, char a, char b, char c) {
        if (num == 1) {
            System.out.println("第1个盘从" + a + "->" + c);
        } else {
            hanoitower(num - 1, a, c, b);
            System.out.println("第" + num + "个盘从" + a + "->" + c);
            hanoitower(num-1,b,a,c);
        }
    }
}
