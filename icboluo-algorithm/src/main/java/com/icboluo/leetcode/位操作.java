package com.icboluo.leetcode;

import org.junit.jupiter.api.Test;

/**
 * @author icboluo
 * @since 2022-08-26 20:19
 */
class 位操作 {
    /**
     * 原码：计算机中将一个数字转换为二进制，并在其最高位加上符号的一种表示方法
     * 反码：根据表示规定，正数的反码就是其本身，而负数的反码是符号位不变，其余各位取反
     * 补码：根据表示规定，正数的补码就是其本身，而负数的补码是其反码+1
     * byte 占1个字节 8位比特位
     * 最高位为符号位，0：正数；1：负数
     * 正数：最大值 01111111 最小值 00000000 +0
     * 负数：最大值 11111111 最小值 10000000 -0（也就是-128
     */
    @Test
    public void test() {
        // 按位与   0110 & 1011 = 0010
        System.out.println(6 & 11);
        // 按位或   0110 | 1011 = 1111
        System.out.println(6 | 11);
        // 按位异或 0110 ^ 1011 = 1101 不一致为1
        System.out.println(6 ^ 11);
        System.out.println("----------------------------------------");
        // 按位取反 0110 ~ 11111111111111111111111111111001 (32位) 是-7
        System.out.println(~6);
        // 左移动  0110<< 2 = 1 1000 不区分正负，左移之后右边补上0
        System.out.println(6 << 2);
        // 带符号右移 1011>> 2 = 0010 右移之后，左边的补上符号位，正数补0，负数补1
        System.out.println(11 >> 2);
        // 无符号右移 1011>>>2 = 0010 无论正负，均补0
        System.out.println(11 >>> 2);
        System.out.println("----------------------------------------");
        System.out.println(Integer.toBinaryString(-7));
        System.out.println(Integer.parseInt("0110", 2));
        System.out.println(Integer.parseUnsignedInt("11111111111111111111111111111001", 2));
        // 这样写会报错
        System.out.println(Integer.parseInt("11111111111111111111111111111001", 2));
    }

    /**
     * 位运算是针对于二进制，进行位运算的时候，首先要将数据转换为二进制
     */
    @Test
    public void base() {
        int i = 2 << 3;
        System.out.println("i = " + i);
        int j = 2 << 4;
        System.out.println("j = " + j);
        // 11--->1100=12
        int k = 3 << 2;
        System.out.println("k = " + k);
    }
}
