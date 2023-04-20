package com.icboluo.leetcode.after_2000;

/**
 * @author icboluo
 * @since 2023-04-20 22:12
 */
class N2299_强密码检查器 {
    public boolean strongPasswordCheckerII(String password) {
        if (password.length() < 8) {
            return false;
        }
        boolean haveSmall = false;
        boolean haveBig = false;
        boolean haveDigit = false;
        boolean haveOther = false;
        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if (ch >= '0' && ch <= '9') {
                haveDigit = true;
            } else if (ch >= 'a' && ch <= 'z') {
                haveSmall = true;
            } else if (ch >= 'A' && ch <= 'Z') {
                haveBig = true;
            } else {
                haveOther = true;
            }
            if (i < password.length() - 1 && ch == password.charAt(i + 1)) {
                return false;
            }
        }
        return haveSmall && haveBig && haveDigit && haveOther;
    }
}
