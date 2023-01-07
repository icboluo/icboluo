package com.icboluo.leetcode.日期;

/**
 * @author icboluo
 * @since 2023-01-07 16:00
 */
class N1507_格式化日期 {
    private String[] monthArr = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

    public String reformatDate(String date) {
        String[] strArr = date.split(" ");
        String year = strArr[2];
        String month = "";
        for (int i = 0; i < monthArr.length; i++) {
            if (strArr[1].equals(monthArr[i])) {
                month = (i + 1) + "";
            }
        }
        month = String.format("%02d", Integer.parseInt(month));
        String day = "";
        for (int i = 0; i < strArr[0].length(); i++) {
            if (Character.isDigit(strArr[0].charAt(i))) {
                day += strArr[0].charAt(i);
            }
        }
        day = String.format("%02d", Integer.valueOf(day));
        return year + "-" + month + "-" + day;
    }
}
