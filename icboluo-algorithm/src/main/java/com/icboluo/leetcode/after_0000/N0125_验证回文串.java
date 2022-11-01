package com.icboluo.leetcode.after_0000;

class N0125_验证回文串 {
    public static void main(String[] args) {
        String str = "A man, a plan, a canal: Panama";
        boolean res = m(str);
        System.out.println("res = " + res);
    }

    private static boolean m(String str) {
        int left = 0;
        int right = str.length() - 1;
        while (left < right) {
            boolean b1 = Character.isLetterOrDigit(str.charAt(left));
            if (!b1) {
                left++;
                continue;
            }
            // 感觉内部使用while循环更稳妥一点
            boolean b2 = Character.isLetterOrDigit(str.charAt(right));
            if (!b2) {
                right--;
                continue;
            }
            if (Character.toLowerCase(str.charAt(left)) != Character.toLowerCase(str.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
