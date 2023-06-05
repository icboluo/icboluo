package com.icboluo.leetcode.low;

import com.icboluo.constant.CharConstant;

import java.util.ArrayList;
import java.util.List;

/**
 * @author icboluo
 * @since 2023-06-05 21:32
 */
class N0824_字符串转换 {

    // low
    public String toGoatLatin(String sentence) {
        String[] arr = sentence.split(" ");
        List<String> res = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            String str = arr[i];
            if (!CharConstant.VOWELS_LIST.contains(str.charAt(0))) {
                str = str.substring(1) + str.charAt(0);
            }
            str += "ma";
            str += "a".repeat(i + 1);
            res.add(str);
        }
        return String.join(" ", res);
    }
}
