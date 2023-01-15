package com.icboluo.leetcode.after_1300;

import java.util.ArrayList;
import java.util.List;

/**
 * @author icboluo
 * @since 2023-01-15 18:51
 */
class N1556_三位数一切割 {
    public String thousandSeparator(int n) {
        // 特例
        if (n == 0) {
            return "0";
        }
        List<String> list = new ArrayList<>();
        while (n >= 1000) {
            list.add(0, String.format("%03d", n % 1000));
            n = n / 1000;
        }
        if (n != 0) {
            list.add(0, n + "");
        }
        return String.join(".", list);
    }

    /**
     * 1560 low
     *
     * @param n
     * @param rounds
     * @return
     */
    public List<Integer> mostVisited(int n, int[] rounds) {
        return null;
    }
}
