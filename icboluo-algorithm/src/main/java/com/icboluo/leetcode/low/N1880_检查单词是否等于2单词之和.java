package com.icboluo.leetcode.low;

/**
 * @author icboluo
 * @since 2023-03-24 0:09
 */
class N1880_检查单词是否等于2单词之和 {
    /**
     * 将字母转换为数字 a为0，b为1...
     *
     * @param firstWord
     * @param secondWord
     * @param targetWord
     * @return
     */
    public boolean isSumEqual(String firstWord, String secondWord, String targetWord) {
        return cal(firstWord) + cal(secondWord) == cal(targetWord);
    }

    private Integer cal(String str) {
        String res = "0";
        for (int i = 0; i < str.length(); i++) {
            if (res.isEmpty() && str.charAt(i) == 'a') {
                continue;
            }
            res += (str.charAt(i) - 'a');
        }
        return Integer.parseInt(res);
    }
}
