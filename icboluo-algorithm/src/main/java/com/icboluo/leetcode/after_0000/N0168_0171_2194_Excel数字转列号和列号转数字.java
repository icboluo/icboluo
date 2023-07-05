package com.icboluo.leetcode.after_0000;

import java.util.ArrayList;
import java.util.List;

/**
 * @author icboluo
 * @since 2022-11-26 11:37
 */
class N0168_0171_2194_Excel数字转列号和列号转数字 {
    public static void main(String[] args) {
        var cla = new N0168_0171_2194_Excel数字转列号和列号转数字();
        System.out.println(cla.cellsInRange("K1:L2"));
    }

    public String convertToTitle(int n) {
        StringBuilder result = new StringBuilder();
        while (n > 0) {
            n--;
            result.insert(0, (char) ('A' + n % 26));
            n /= 26;
        }
        return result.toString();
    }

    public String convertToTitle2(int n) {
        return n == 0 ? "" : convertToTitle2(--n / 26) + (char) ('A' + (n % 26));
    }

    /**
     * 0171 Excel 列号转数字
     * 我们可以发现 BCM=(2*26+3)26+13
     *
     * @param columnTitle 列英文名称，不区分大小写
     * @return 列对应的序号，从1开始
     */
    public int titleToNumber(String columnTitle) {
        if (columnTitle == null) {
            return -1;
        }
        int sum = 0;
        for (char ch : columnTitle.toUpperCase().toCharArray()) {
            sum *= 26;
            sum += ch - 'A' + 1;
        }
        return sum;
    }

    /**
     * 1030 low
     *
     * @param rows
     * @param cols
     * @param rCenter
     * @param cCenter
     * @return
     */
    public int[][] allCellsDistOrder(int rows, int cols, int rCenter, int cCenter) {
        return null;
    }

    /**
     * 2194 Excel工作表上范围内的单元格
     *
     * @param s
     * @return
     */
    public List<String> cellsInRange(String s) {
        // 顺序呢是上到下，左到右
        int up = Character.getNumericValue(s.charAt(1));
        int down = Character.getNumericValue(s.charAt(4));
        char left = s.charAt(0);
        char right = s.charAt(3);
        List<String> list = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            // 首先判断 上下的位置
            for (int j = up; j <= down; j++) {
                String temp = Character.toString(i) + j;
                list.add(temp);
            }
        }
        return list;
    }
}
