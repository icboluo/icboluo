package com.icboluo.leetcode.after_0200;

/**
 * @author icboluo
 * @since 2022-11-09 13:07
 */
class N0520_0709_2129转换成小写 {
    // 0520检测资本
    public boolean detectCapitalUse(String word) {
        boolean allIsUpper = true;
        boolean allIsLower = true;
        boolean firstIsUpper = true;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (ch >= 'a' && ch <= 'z') {
                allIsUpper = false;
                if (i == 0) {
                    firstIsUpper = false;
                }
            }else{
                allIsLower = false;
                if (i != 0) {
                    firstIsUpper = false;
                }
            }
        }
        return allIsLower || allIsUpper || firstIsUpper;
    }

    // 0709 转换成小写
    public String toLowerCase(String s) {
        String str = "";
        for (char ch : s.toCharArray()) {
            if (ch >= 'A' && ch <= 'Z') {
                char temp = (char) ('a' - 'A' + ch);
                str += temp;
            } else {
                str += ch;
            }
        }
        return str;
    }

    // 2129 大写标题
    public String capitalizeTitle(String title) {
        String[] word = title.split(" ");
        String ans = "";
        for (int i = 0; i < word.length; i++) {
            if (word[i].length() > 2) {
                ans += word[i].substring(0, 1).toUpperCase() + word[i].substring(1).toLowerCase();
            } else {
                ans += word[i].toLowerCase();
            }
            if (i != word.length - 1) {
                ans += " ";
            }
        }
        return ans;
    }
}
