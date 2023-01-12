package com.icboluo.leetcode.math;

class N0172_阶乘后的零 {
    public static void main(String[] args) {
        var cla = new N0172_阶乘后的零();
        int res = cla.trailingZeroes1(5);
        System.out.println("res = " + res);
    }

    /**
     * 阶乘 FIXME ERROR
     *
     * @param n
     * @return
     */
    public int trailingZeroes1(int n) {
        if (n == 1) {
            return n;
        }
        int res = n * trailingZeroes1(n - 1);
        return res;
    }

    /**
     * 2和5解法，因为0是由2*5得来的，并且，5比2要少，所以，5的个数决定了0的个数；对于25来说，由2个5构成
     *
     * @param n
     * @return
     */
    public int trailingZeroes2(int n) {
        if (n == 1) {
            return n;
        }
        int res = n * trailingZeroes2(n - 1);
        return res;
    }
}
