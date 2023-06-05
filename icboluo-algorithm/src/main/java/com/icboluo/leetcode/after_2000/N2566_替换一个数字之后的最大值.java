package com.icboluo.leetcode.after_2000;

/**
 * @author icboluo
 * @since 2023-06-05 21:42
 */
class N2566_替换一个数字之后的最大值 {
    // 重新映射数字的最大差异
    // 改变数字，求最大最小差
    public int minMaxDifference(int num) {
        String numStr = String.valueOf(num);
        char[] chars = numStr.toCharArray();
        char ele = chars[0];
        String min = numStr.replace(ele, '0');
        // 找到第一个不为9的数
        for (char ch : chars) {
            if (ch != '9') {
                ele = ch;
                break;
            }
        }
        String max = numStr.replace(ele, '9');

        return Integer.parseInt(max) - Integer.parseInt(min);
    }
}
