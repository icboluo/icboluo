package com.icboluo.leetcode.滑动窗口;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author icboluo
 * @since 2023-05-17 0:36
 */
class N2062_计算字符串的元音子串 {
    // 滑动窗口，这个题属于中等问题 FIXME ERROR
    public int countVowelSubstrings(String word) {
        Map<Character, Integer> eleCountMap = new HashMap<>();
        int valid = 0;
        int l = 0;
        // 标记全元音子串的开始
        int r = 0;
        // mid->r标记最小元音子串的长度
        int mid = 0;
        int count = 0;
        while (r < word.length()) {
            // 右指针右移扩大解
            char right = word.charAt(r++);
            if (VOWELS_LIST.contains(right)) {
                eleCountMap.put(right, eleCountMap.getOrDefault(right, 0) + 1);
                if (eleCountMap.get(right) == 1) {
                    valid++;
                }
                // 当满足解的条件的时候,移动mid指针，直到mid指针满足最优解
                while (valid == 5) {
                    char miVal = word.charAt(mid++);
                    eleCountMap.put(miVal, eleCountMap.get(miVal) - 1);
                    if (eleCountMap.get(miVal) == 0) {
                        valid--;
                    }
                }
                // 这个需要放到while的外面，此块代码非常难写，当mid和left有不同的时候，说明mid指针动了，产生了偏差，所以需要记录结果
                // 这种累加计数方式需要一次性记录
                count += mid - l;
            } else {
                eleCountMap.clear();
                valid = 0;
                // 本次是非元音，所以至少，l和mid应该位于下一个字符处
                l = r;
                mid = r;
            }
        }
        return count;
    }

    /**
     * 元音列表
     */
    public static final List<Character> VOWELS_LIST = Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');
}
