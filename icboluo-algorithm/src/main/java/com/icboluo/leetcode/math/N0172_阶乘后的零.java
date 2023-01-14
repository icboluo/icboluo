package com.icboluo.leetcode.math;

class N0172_阶乘后的零 {
    public static void main(String[] args) {
        var cla = new N0172_阶乘后的零();
        int res = cla.trailingZeroes1(5);
        System.out.println("res = " + res);
    }

    /**
     * 阶乘
     *
     * @param n
     * @return
     */
    public int trailingZeroes1(int n) {
        if (n == 1) {
            return n;
        }
        return n * trailingZeroes1(n - 1);
    }

    /**
     * 2和5解法，因为0是由2*5得来的，并且，5比2要少，所以，5的个数决定了0的个数；对于25来说，由2个5构成
     *
     * @param n
     * @return
     */
    public int trailingZeroes2(int n) {
        int count = 0;
        // 比如第一次循环 5 10 15 20 25 30，阶乘中存在6个5；1,2,3,4,5,6，第二个阶乘中存在1个5，总的就是6+1
        // 这个解决方法是巧妙的，类似于子数组的组合数
        while (n != 0) {
            // 阶乘中有多少个5
            int temp = n / 5;
            count += temp;
            n = temp;
        }
        return count;
    }
}
