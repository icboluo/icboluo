package com.icboluo.leetcode.low;

/**
 * @author icboluo
 * @since 2023-03-30 21:47
 */
class N2600_总和最大的k项 {
    // 总和最大的k项 low
    public int kItemsWithMaximumSum(int numOnes, int numZeros, int numNegOnes, int k) {
        if (k < numOnes) {
            return k;
        }
        if (k < numOnes + numZeros) {
            return numOnes;
        }
        if (k < numOnes + numZeros + numNegOnes) {
            return numOnes - (k - numOnes - numZeros);
        }
        return numOnes - numNegOnes;
    }
}
