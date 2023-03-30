package com.icboluo.leetcode.math;

/**
 * @author icboluo
 * @since 2023-03-30 21:59
 */
class N2259_从数字中删除数字以最大结果化 {
    // 方案1：挨着删除digit之后汇总比较 FIXME ERROR
    // 方案2：我们需要判断，如果该字符的下一个字符比较大，说明删这个字符是合理的，让数字变大
    public String removeDigit(String number, char digit) {
        for (int i = 0; i < number.length() - 1; i++) {
            if (digit == Character.digit(number.charAt(i), 10) && number.charAt(i + 1) > number.charAt(i)) {
                return number.substring(0, i) + number.substring(i + 1);
            }
        }
        int last = number.lastIndexOf(digit);
        return number.substring(0, last) + number.substring(last + 1);
    }
}
