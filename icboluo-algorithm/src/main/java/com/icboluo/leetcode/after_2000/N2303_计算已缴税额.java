package com.icboluo.leetcode.after_2000;

/**
 * @author icboluo
 * @since 2023-04-20 22:17
 */
class N2303_计算已缴税额 {
    /**
     * @param brackets
     * @param income
     * @return
     */
    public double calculateTax(int[][] brackets, int income) {
        int res = 0;
        for (int i = 0; i < brackets.length; i++) {
            int val = brackets[i][0];
            int preVal = i == 0 ? 0 : brackets[i - 1][0];
            int rate = brackets[i][1];
            // 收入过大
            if (income > val) {
                res += (val - preVal) * rate;
            } else {
                res += (income - preVal) * rate;
                return res / 100.0;
            }
        }
        return res / 100.0;
    }
}
