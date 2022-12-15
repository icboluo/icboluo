package com.icboluo.leetcode.after_0000;

/**
 * @author icboluo
 * @since 2022-03-25 19:52
 */
class N0009_回文数 {

    public boolean isPalindrome(int x) {
        return hws1(x);
//        return hws2(x);
    }

    /**
     * 用sb的reverse方法判断
     *
     * @param x
     * @return
     */
    private boolean hws1(int x) {
        // new sb 数字参数是容量不是具体指
        return new StringBuilder(x + "").reverse().compareTo(new StringBuilder(x + "")) == 0;
    }

    /**
     * 用/%取左右比较
     *
     * @param x 元素 2002
     * @return 是否回文数
     */
    private boolean hws2(int x) {
        if (x < 0) {
            return false;
        }
        int weiShu = 1;
        // 这里注意等号
        while (x / weiShu >= 10) {
            weiShu *= 10;
        }
        while (weiShu != 0) {
            // 首位
            int left = x / weiShu;
            // 末位
            int right = x % 10;
            if (left != right) {
                return false;
            }
            x = (x % weiShu) / 10;
            weiShu /= 100;
        }
        return true;
    }

    /**
     * 后半段反转和前半段比较
     *
     * @param x 元素 2002
     * @return 是否回文数
     */
    private boolean hws3(int x) {
        if (x < 0) {
            return false;
        }
//        int revert = 0;
//        while (x > revert) {
//            // 最后一位数*10+%的值
//            revert = revert * 10 + x % 10;
//            x /= 10;
//        }
//        return x == revert || x == revert / 10;
        String iStr = x + "";
        String revert = "";
        while (revert.length() < iStr.length() / 2) {
            revert = revert + x % 10;
            x /= 10;
        }
        return revert.equals(String.valueOf(x)) || revert.equals(String.valueOf(x).substring(0, revert.length()));
    }
}
