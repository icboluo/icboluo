package com.icboluo.leetcode.low;

/**
 * @author icboluo
 * @since 2023-04-21 0:07
 */
class N2490_循环句 {
    /**
     * 上一个单词的最后一个字母是否等于下一个单词的第一个字母
     *
     * @param sentence
     * @return
     */
    public boolean isCircularSentence(String sentence) {
        for (int i = 0; i < sentence.length(); i++) {
            if (sentence.charAt(i) == ' ' && sentence.charAt(i - 1) != sentence.charAt(i + 1)) {
                return false;
            }
        }
        return sentence.charAt(0) == sentence.charAt(sentence.length() - 1);
    }
}
