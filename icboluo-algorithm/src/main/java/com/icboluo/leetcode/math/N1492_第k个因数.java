package com.icboluo.leetcode.math;

/**
 * @author icboluo
 * @since 2023-12-18 22:52
 */
class N1492_第k个因数 {
    public static void main(String[] args) {
        var cla = new N1492_第k个因数();
        System.out.println(cla.kthFactor(12, 3));
        System.out.println(cla.kthFactor(7, 2));
        System.out.println(cla.kthFactor(4, 4));
    }

    public int kthFactor(int n, int k) {
        for (int i = 1; i < Math.sqrt(n); i++) {
            if (n % i == 0 && --k == 0) {
                return i;
            }
        }
        for (int i = (int) Math.sqrt(n); i > 0; i--) {
            if (n % i == 0 && --k == 0) {
                return n / i;
            }
        }
        return -1;
    }
}
