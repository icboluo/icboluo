package com.icboluo.leetcode.高效寻找素数;

import java.util.stream.IntStream;

/**
 * @author icboluo
 * @since 2023-01-14 18:47
 */
class N0204_统计小于n的素数个数 {
    public int countPrimes(int n) {
        if (n == 0 || n == 1) {
            return 0;
        }
        boolean[] notPrime = new boolean[n];
        notPrime[0] = true;
        notPrime[1] = true;
        // 这里的条件也比较重要，到log级为止
        for (int i = 2; i * i < n; i++) {
            if (!notPrime[i]) {
                // 针对于5，没有必要计算5*2和5*3了，因为2的整数倍、3的整数倍的时候已经计算过了；每次j的迭代是i的整数倍；不知道下面2种方式那种更好接受一些
                for (int j = i * i; j < n; j += i) {
                    notPrime[j] = true;
                }
/*                for (int j = i; j * i < n; j++) {
                    notPrime[j] = true;
                }*/
            }
        }
        return (int) IntStream.range(0, n).filter(i -> !notPrime[i]).count();
    }
}
