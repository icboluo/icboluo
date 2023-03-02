package com.icboluo.leetcode.位运算;

/**
 * @author icboluo
 * @since 2023-03-02 21:02
 */
class N1837_进制转换 {
    // 将10进制的n转换为k进制，返回每位数之和,也可以用进制转换来完成
    public int sumBase(int n, int k) {
        int sum = 0;
        while (n >= k) {
            sum += n % k;
            n = n / k;
        }
        sum += n;
        return sum;
    }
}
