package com.icboluo.leetcode.after_1300;

/**
 * @author icboluo
 * @since 2023-01-15 18:58
 */
class N1566_1668_检查长度为m的模式重复多次 {
    /**
     * 是否存在连续重复的字符串k次，这个问题并不简单
     *
     * @param arr
     * @param m   子串长度
     * @param k   k次
     * @return
     */
    public boolean containsPattern(int[] arr, int m, int k) {
        int count = 0;
        for (int i = 0; i < arr.length - m; i++) {
            // 因为是连续的，所以我们弄出来 m*k个正确的数即可
            if (arr[i] == arr[i + m]) {
                count++;
                if (count == m * (k - 1)) {
                    return true;
                }
            } else {
                count = 0;
            }
        }
        return false;
    }

    /**
     * 1668 最大重复子串，一样的要求连续
     *
     * @param sequence
     * @param word
     * @return
     */
    public int maxRepeating(String sequence, String word) {
        int ans = 1;
        while (sequence.contains(word.repeat(ans))) {
            ans++;
        }
        return ans - 1;
    }

    /**
     * 0459 重复子串模式：字符串是否可以拆成几份相同的
     * 一个字符串可以写成 a a a，则2个字符串加起来去头去尾，就会写成 b a a a a b包含当前字符串
     *
     * @param s
     * @return
     */
    public boolean repeatedSubstringPattern(String s) {
        String two = s + s;
        return two.substring(1, two.length() - 1).contains(s);
    }

    /**
     * 0028 查找字符串中第一次出现的索引 indexOf
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr1(String haystack, String needle) {
        return haystack.indexOf(needle);
    }

    /**
     * 挺巧妙的代码
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr2(String haystack, String needle) {
        for (int i = 0; i < haystack.length(); i++) {
            // 这里的界限由方法内确定即可
            for (int j = 0; ; j++) {
                if (j == needle.length()) {
                    return i;
                }
                if (i + j == haystack.length()) {
                    return -1;
                }
                if (needle.charAt(j) != haystack.charAt(i + j)) {
                    break;
                }
            }
        }
        return -1;
    }

    /**
     * 0686 重复字符串匹配；a重复多次之后，b为a的子串
     * 需要正面：a和b一样长的时候，a*2 是否包含b就决定了结果
     *
     * @param a
     * @param b
     * @return
     */
    public int repeatedStringMatch(String a, String b) {
        String str = "";
        int count = 0;
        while (str.length() < b.length()) {
            str += a;
            count++;
        }
        if (str.contains(b)) {
            return count;
        }
        if ((str + a).contains(b)) {
            return count + 1;
        }
        return -1;
    }
}
