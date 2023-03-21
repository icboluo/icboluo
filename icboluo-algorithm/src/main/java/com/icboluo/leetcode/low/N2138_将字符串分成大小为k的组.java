package com.icboluo.leetcode.low;

import java.util.ArrayList;
import java.util.List;

/**
 * @author icboluo
 * @since 2023-03-21 23:34
 */
class N2138_将字符串分成大小为k的组 {
    /**
     * 也可以从总体的角度解决问题 low
     *
     * @param s
     * @param k
     * @param fill
     * @return
     */
    public String[] divideString(String s, int k, char fill) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < s.length(); i += k) {
            if (i + k < s.length()) {
                res.add(s.substring(i, i + k));
            } else {
                String str = s.substring(i);
                while (str.length() < k) {
                    str += fill;
                }
                res.add(str);
            }
        }
        return res.toArray(String[]::new);
    }
}
