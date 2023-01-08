package com.icboluo.leetcode.位运算;

import org.junit.jupiter.api.Test;

import java.util.List;

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

    /**
     * 利用ascii编码
     */
    @Test
    public void lowCase() {
        // 利用 或 操作和 空格将英文字符转换为小写
        int a = 'a' | ' ';
        int b = 'A' | ' ';
        // 利用 与 操作和 下划线将英文字符转换为大写
        int c = 'b' | '_';
        int d = 'B' | '_';
        // 利用 异或 操作和 空格进行英文字符大小写互换
        int e = 'c' ^ ' ';
        int f = 'C' ^ ' ';
    }

    /**
     * 利用的是补码编码的符号位 判断2数是否异号
     */
    @Test
    public void isDiffFuHao() {
        int x = -1, y = 2;
        boolean f1 = ((x ^ y) < 0);

        int z = 3;
        boolean f2 = ((y ^ z) < 0);
    }

    /**
     * 不使用临时变量交换2个元素·123
     */
    @Test
    public void exchange() {
        int a = 1, b = 2;
        a ^= b;
        // b=b^a^b=a
        b ^= a;
        // a=a^b^a=b
        a ^= b;
    }

    /**
     * 消除二进制表示的最后一个1
     */
    @Test
    public void removeLastOne() {
        int n = 10;
        // 1010&1001=1000
        int a = n & (n - 1);
    }

    /**
     * 计算汉明权重
     * 返回一个数二进制表达的1的个数
     */
    @Test
    public void hmqz() {
        int n = 100;
        int count = 0;
        while (n != 0) {
            n = n & (n - 1);
            count++;
        }
        System.out.println(count);
    }

    /**
     * 判断一个数是不是2的指数
     */
    @Test
    public void edzs(int n) {
        // 一个数如果是2的指数，那么它的二进制表示一定只含有1个1
        if (n < 0) {
            System.out.println(false);
        }
        boolean b = (n & (n - 1)) == 0;
        System.out.println(b);
    }

    /**
     * 查找只出现一次的元素
     *
     * @param list 只有1个元素出现了1次，其他的出现了偶数次数
     */
    @Test
    public void showOneTime(List<Integer> list) {
        int res = 0;
        for (Integer item : list) {
            res ^= item;
        }
        System.out.println(res);
    }
}
