package com.icboluo.leetcode.low;

/**
 * @author icboluo
 * @since 2023-03-30 21:54
 */
class N2119_数字经过2次反转是否还是原来的数字 {

    public boolean isSameAfterReversals(int num) {
        return num == 0 || num % 10 != 0;
    }
}
