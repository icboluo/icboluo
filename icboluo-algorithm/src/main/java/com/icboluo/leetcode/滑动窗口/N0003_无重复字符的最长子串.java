package com.icboluo.leetcode.滑动窗口;

import java.util.HashMap;
import java.util.Map;

/**
 * 滑动窗口中可以存储元素出现的次数，也可以存元素出现的最后一个索引（特殊情况下），这个时候可以不对滑动窗口的数据进行销毁，
 * 因为索引是有记忆位置的功能，但是要注意在滑动窗口内取值进行处理
 *
 * @author icboluo
 * @since 2022-10-27 13:27
 */
class N0003_无重复字符的最长子串 {
    public static void main(String[] args) {
        N0003_无重复字符的最长子串 cla = new N0003_无重复字符的最长子串();
        String str = "abcabcbb";
        int res = cla.lengthOfLongestSubstring(str);
        System.out.println("res = " + res);
    }

    /**
     * 常规解法
     *
     * @param str
     * @return
     */
    public int lengthOfLongestSubstring(String str) {
        int ans = 0;
        // 字符出现的次数
        Map<Character, Integer> winMap = new HashMap<>();
        int l = -1;
        int r = -1;
        // 右指针扩大
        while (r < str.length() - 1) {
            // 这种情况我们将右指针放进结果集，就应该对右指针进行处理
            char right = str.charAt(++r);
            winMap.put(right, winMap.getOrDefault(right, 0) + 1);
            // 左指针扩大，寻找最优解，这里有先后关系；首先应该循环玩这个，才可能有最优解
            while (winMap.get(right) > 1) {
                char left = str.charAt(++l);
                // 这里是窗口的数据销毁功能
                winMap.put(left, winMap.getOrDefault(left, 0) - 1);
            }
            // 求的是最长，所以应该在每次增大右边界的时候收集一次
            ans = Math.max(ans, r - l);
        }
        return ans;
    }

    // TODO error
    public int m2(String str) {
        int ans = 0;
        // 字符最后一次出现的索引
        Map<Character, Integer> winMap = new HashMap<>();
        int l = 0;
        int r = 0;
        // 右指针扩大，遍历所有解
        while (r < str.length() - 1) {
            char right = str.charAt(r++);
            // 左指针右移，优化最优解，这里采用一次移动到左指针的位置或者出现过的下一个位置
            // 其实好像做个判断好一点，如果窗口中不存在就不需要移动左指针
            l = Math.max(winMap.getOrDefault(right, -1) + 1, l);
            // 因为这一块只有窗口的数据录入功能，没有销毁功能，所以，取值一定要保证在窗口内部，也就是必须要不小于left
            winMap.put(right, r);
            ans = Math.max(ans, r - l);
        }
        return ans;
    }

    /**
     * m2的for循环型解法 TODO ERROR
     *
     * @param str
     * @return max length
     */
    public int m3(String str) {
        // 字符最后一次出现的索引
        Map<Character, Integer> winMap = new HashMap<>();
        int l = 0;
        int r = 0;
        int max = Integer.MIN_VALUE;

        char[] chars = str.toCharArray();
        for (; r < chars.length && l < chars.length; r++) {
            char right = chars[r];
            // 如果map中已经存在，左指针要移动到当前元素最后一次出现的右边
            if (winMap.containsKey(right)) {
                // 已经存在但是必须要比left大，要不然没有到滑动窗口中
                l = Math.max(winMap.get(chars[r]) + 1, l);
            }
            winMap.put(right, r);
            max = Math.max(max, r - l + 1);
        }
        return max;
    }
}
