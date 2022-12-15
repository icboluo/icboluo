package com.icboluo.leetcode.after_0800;

/**
 * @author icboluo
 * @since 2022-12-05 23:35
 */
class N0925_长按名字 {
    public static void main(String[] args) {
        N0925_长按名字 cla = new N0925_长按名字();
        boolean longPressedName = cla.isLongPressedName("alex", "aaleexa");
        System.out.println("longPressedName = " + longPressedName);
    }

    /**
     * 2个字符串是否大致匹配
     * 双指针
     *
     * @param name
     * @param typed
     * @return
     */
    public boolean isLongPressedName(String name, String typed) {
        int i = 0;
        int j = 0;
        while (i < name.length() && j < typed.length()) {
            if (name.charAt(i) == typed.charAt(j)) {
                i++;
                // 如果当前字符和前置字符相等，说明匹配，否则异常
            } else if (j == 0 || typed.charAt(j) != typed.charAt(j - 1)) {
                return false;
            }
            j++;
        }
        // 注意尾部情况
        while (j < typed.length()) {
            if (typed.charAt(j - 1) != typed.charAt(j)) {
                return false;
            }
            j++;
        }
        return i == name.length() && j == typed.length();
    }
}
