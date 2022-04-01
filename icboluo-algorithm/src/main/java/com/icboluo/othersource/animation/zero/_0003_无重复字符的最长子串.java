package com.icboluo.othersource.animation.zero;

/**
 * @author icboluo
 * @date 2020-09-27 20:34
 */
public class _0003_无重复字符的最长子串 {
    public static void main(String[] args) {
        String str = "abcabcbb";
        char[] chars = str.toCharArray();
        m1(chars);
    }

    /**
     * 双层for循环
     *
     * @param chars
     * @return
     */
    private static void m1(char[] chars) {
        for (int i = 0; i < chars.length - 1; i++) {
            for (int j = i + 1; j < chars.length; j++) {

            }
        }
    }

    /**
     * 双指针，把不重复的内如括起来，找到最大的区域返回即可
     *
     * @param chars
     * @return
     */
    private static void m2(char[] chars) {

    }
}
