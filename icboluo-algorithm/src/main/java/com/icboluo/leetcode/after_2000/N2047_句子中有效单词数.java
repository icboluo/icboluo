package com.icboluo.leetcode.after_2000;

/**
 * @author icboluo
 * @since 2023-03-16 22:47
 */
class N2047_句子中有效单词数 {
    // 长单词中有效单词的数量 low FIXME ERROR
    public int countValidWords(String sentence) {
        String[] arr = sentence.split(" ");
        int count = 0;
        for (String str : arr) {
            boolean isValid = true;
            for (int i = 0; i < str.length(); i++) {
                if (Character.isDigit(str.charAt(i))) {
                    isValid = false;
                }
                if (i != str.length() - 1 && (str.charAt(i) == '.' || str.charAt(i) == '!' || str.charAt(i) == ',')) {
                    isValid = false;
                }
            }
            if (str.indexOf('-') == 0 || str.indexOf('-') == str.length() - 1 || str.indexOf('-') != str.lastIndexOf('-')) {
                isValid = false;
            }
            if (isValid) {
                count++;
            }
        }
        return count;
    }
}
