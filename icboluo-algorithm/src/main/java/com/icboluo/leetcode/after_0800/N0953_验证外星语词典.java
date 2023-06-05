package com.icboluo.leetcode.after_0800;

/**
 * @author icboluo
 * @since 2023-06-05 23:27
 */
class N0953_验证外星语词典 {
    int[] arr = new int[26];

    // 验证外星字典
    public boolean isAlienSorted(String[] words, String order) {
        // 按字典序赋权重
        for (int i = 0; i < order.length(); i++) {
            arr[order.charAt(i) - 'a'] = i;
        }
        // 逐个比较确定顺序
        for (int i = 0; i < words.length - 1; i++) {
            if (moreThan(words[i], words[i + 1])) {
                return false;
            }
        }
        return true;
    }

    private boolean moreThan(String str1, String str2) {
        for (int i = 0; i < str1.length() && i < str2.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                return arr[str1.charAt(i) - 'a'] > arr[str2.charAt(i) - 'a'];
            }
        }
        return str1.length() > str2.length();
    }
}
