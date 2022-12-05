package com.icboluo.leetcode.位运算;

/**
 * @author icboluo
 * @since 2022-12-05 23:48
 */
class N1009_10进制整数的补数 {
    public static void main(String[] args) {
        N1009_10进制整数的补数 cla = new N1009_10进制整数的补数();
        int res = cla.bitwiseComplement2(5);
        System.out.println("res = " + res);
    }

    public int bitwiseComplement1(int n) {
        // 让x变为 1111 的形式
        int x = 1;
        while (n > x) {
            x = x * 2 + 1;
        }
        // 1111-1001=0110 ^ 的结果也是一样的，2种方式都可以
        return x ^ n;
    }

    public int bitwiseComplement2(int n) {
        // 让x变为 1111 的形式
        int x = 1;
        while (n > x) {
            x = (x << 1) + 1;
        }
        // 1111-1001=0110 ^ 的结果也是一样的，2种方式都可以
        return x - n;
    }
}
