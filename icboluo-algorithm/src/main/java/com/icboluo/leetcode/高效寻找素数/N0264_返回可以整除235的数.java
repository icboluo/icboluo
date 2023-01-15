package com.icboluo.leetcode.高效寻找素数;

/**
 * @author icboluo
 * @since 2023-01-14 18:54
 */
class N0264_返回可以整除235的数 {
    // FIXME ERROR
    public int nthUglyNumber(int n) {
        if (n == 1 || n == 2) {
            return n;
        }
        // 改成map更好，要不然这个数组的范围界定有点麻烦
        boolean[] isUsed = new boolean[2 * n + 10];
        int a = 2;
        int b = 3;
        int c = 5;
        for (int i = 0; i < n - 1; ) {
            int min = Math.min(Math.min(a, b), c);
            if (!isUsed[min]) {
                isUsed[min] = true;
                i++;
            }
            if (a == min) {
                a += 2;
            } else if (b == min) {
                b += 3;
            } else {
                c += 5;
            }
        }
        return Math.min(Math.min(a, b), c);
    }

    /**
     * 0279 完美正方形；把n拆为多份，没份都是完美平方，求最少份数 TODO 挺复杂的，解法多样化
     *
     * @param n
     * @return
     */
    public int numSquares(int n) {
        return -1;
    }


    /**
     * 0313 超级丑数；返回第n个丑数
     *
     * @param n
     * @param primes
     * @return
     */
    public int nthSuperUglyNumber(int n, int[] primes) {
        return -1;
    }
}
