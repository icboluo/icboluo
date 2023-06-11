package com.icboluo.leetcode.low;

/**
 * @author icboluo
 * @since 2023-03-16 22:47
 */
class N2047_句子中有效单词数 {
    // 长单词中有效单词的数量
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
            if (str.isEmpty()) {
                isValid = false;
            }
            int idx = str.indexOf('-');
            if (idx != -1) {
                if (idx == 0 || idx == str.length() - 1 || idx != str.lastIndexOf('-')
                        || !Character.isLetter(str.charAt(idx - 1)) || !Character.isLetter(str.charAt(idx + 1))) {
                    isValid = false;
                }
            }
            if (isValid) {
                count++;
            }
        }
        return count;
    }
}
