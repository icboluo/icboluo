package com.icboluo.leetcode.after_1300;

import java.util.Iterator;

/**
 * @author icboluo
 * @since 2023-04-20 21:35
 */
public class N1662_检查2个字符串是否等价 {
    /**
     * 判断2个数组里面的字符串+起来是否相等
     * 我们不要简单的去加起来，如果输入的数据比较大的时候，字符串相加对于时间和空间是有浪费的
     *
     * @param word1
     * @param word2
     * @return
     */
    public boolean arrayStringsAreEqual1(String[] word1, String[] word2) {
        int diJiGeDanCi1 = 0;
        int diJiGeDanCi2 = 0;
        int charIdx1 = 0;
        int charIdx2 = 0;
        while (diJiGeDanCi1 < word1.length && diJiGeDanCi2 < word2.length) {
            String str1 = word1[diJiGeDanCi1];
            String str2 = word2[diJiGeDanCi2];
            if (str1.charAt(charIdx1) != str2.charAt(charIdx2)) {
                return false;
            }
            // 此块代码具有重复性，如果求多个数组+起来是否相等就很糟糕，可以做一个迭代器
            if (charIdx1 == str1.length() - 1) {
                charIdx1 = 0;
                diJiGeDanCi1++;
            } else {
                charIdx1++;
            }
            if (charIdx2 == str2.length() - 1) {
                charIdx2 = 0;
                diJiGeDanCi2++;
            } else {
                charIdx2++;
            }
        }
        return diJiGeDanCi1 == word1.length && diJiGeDanCi2 == word2.length;
    }

    /**
     * FIXME ERROR
     *
     * @param word1
     * @param word2
     * @return
     */
    public boolean arrayStringsAreEqual2(String[] word1, String[] word2) {
        CharIterator iter1 = new CharIterator(word1, 0, 0);
        CharIterator iter2 = new CharIterator(word2, 0, 0);
        while (iter1.hasNext() && iter2.hasNext()) {
            if (!iter1.next().equals(iter2.next())) {
                return false;
            }
        }
        return !iter1.hasNext() && !iter2.hasNext();
    }

    class CharIterator implements Iterator<Character> {
        String[] words;
        int diJiGeDanCi;
        int charIdx;

        @Override
        public boolean hasNext() {
            return diJiGeDanCi < words.length;
        }

        @Override
        public Character next() {
            char ch = words[diJiGeDanCi].charAt(charIdx);
            if (charIdx == words[diJiGeDanCi].length()) {
                diJiGeDanCi++;
                charIdx = 0;
            } else {
                charIdx++;
            }
            return ch;
        }

        public CharIterator(String[] words, int diJiGeDanCi, int charIdx) {
            this.words = words;
            this.diJiGeDanCi = diJiGeDanCi;
            this.charIdx = charIdx;
        }
    }

}
