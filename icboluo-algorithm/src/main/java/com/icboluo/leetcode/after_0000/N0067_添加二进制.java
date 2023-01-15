package com.icboluo.leetcode.after_0000;

/**
 * @author icboluo
 * @since 2022-11-03 21:17
 */
class N0067_添加二进制 {
    public static void main(String[] args) {
        var cla = new N0067_添加二进制();
        String s = cla.addBinary("11", "1");
        System.out.println("s = " + s);
    }

    /**
     * 代码没啥问题，字符串过长会造成数字格式化异常，AC不了
     *
     * @param a
     * @param b
     * @return
     */
    public String addBinary(String a, String b) {
        int aInt = Integer.parseInt(a, 2);
        int bInt = Integer.parseInt(b, 2);
        int sum = aInt + bInt;
        return Integer.toString(sum, 2);
    }

    /**
     * 标准解法
     *
     * @param a
     * @param b
     * @return
     */
    public String addBinary2(String a, String b) {
        int i = a.length() - 1;
        int j = b.length() - 1;
        int jinZi = 0;
        StringBuilder sb = new StringBuilder();
        while (i >= 0 || j >= 0) {
            int sum = jinZi;
            if (i >= 0) {
                sum += a.charAt(i--) - '0';
            }
            if (j >= 0) {
                sum += b.charAt(j--) - '0';
            }
            jinZi = sum / 2;
            sb.append(sum % 2);
        }
        if (jinZi != 0) {
            sb.append(jinZi);
        }
        return sb.reverse().toString();
    }
}
