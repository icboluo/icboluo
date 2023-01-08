package com.icboluo.leetcode.排序;

import java.util.Arrays;

/**
 * @author icboluo
 * @since 2023-01-07 23:53
 */
class N1356_按2进制1的个数进行排序 {
    public int[] sortByBits(int[] arr) {
        return Arrays.stream(arr).boxed().sorted((a,b)->{
            int aNum = Integer.toBinaryString(a).replace("0", "").length();
            int bNum = Integer.toBinaryString(b).replace("0", "").length();
            if (aNum != bNum) {
                return aNum - bNum;
            }
            return a - b;
        }).mapToInt(Integer::intValue).toArray();
    }
}
