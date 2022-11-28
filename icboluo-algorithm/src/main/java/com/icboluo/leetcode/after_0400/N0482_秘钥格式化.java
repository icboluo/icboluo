package com.icboluo.leetcode.after_0400;

import java.util.Locale;

/**
 * @author icboluo
 * @since 2022-11-26 11:19
 */
class N0482_秘钥格式化 {
    public String licenseKeyFormatting(String s, int k) {
        String replace = s.replace("-", "").toUpperCase(Locale.ROOT);
        String res = "";
        for (int r = replace.length(); r > 0; r -= k) {
            if ("".equals(res)) {
                res = replace.substring(Math.max(0, r - k), r);
            } else {
                res = replace.substring(Math.max(0, r - k), r) + "-" + res;
            }
        }
        return res;
    }
}
