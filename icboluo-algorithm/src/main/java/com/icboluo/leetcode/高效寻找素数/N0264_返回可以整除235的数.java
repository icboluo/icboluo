package com.icboluo.leetcode.高效寻找素数;

/**
 * @author icboluo
 * @since 2023-01-14 18:54
 */
class N0264_返回可以整除235的数 {
    public static void main(String[] args) {
        var cla = new N0264_返回可以整除235的数();
        System.out.println(cla.nthUglyNumber(10));
    }

    // FIXME ERROR
    public int nthUglyNumber(int n) {
        // 请使用乘法，而不是加法
        int a2 = 1;
        int a3 = 1;
        int a5 = 1;
        int[] arr = new int[n];
        arr[0] = 1;
        for (int i = 1; i < n; i++) {
            // 我们每次获取下一个可能的最小值
            arr[i] = Math.min(Math.min(a2 * 2, a3 * 3), a5 * 5);
            // 这里如果出现公倍数，多个指针会同时变化，会将较小的数提升到较高的级别，保证了程序顺序进行
            // 绝不能加else
            if (a2 * 2 == arr[i]) {
                a2++;
            }
            if (a3 * 3 == arr[i]) {
                a3++;
            }
            if (a5 * 5 == arr[i]) {
                a5++;
            }
        }
        return arr[n - 1];
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
