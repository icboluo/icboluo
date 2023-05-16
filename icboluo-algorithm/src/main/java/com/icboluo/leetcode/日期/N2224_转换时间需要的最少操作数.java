package com.icboluo.leetcode.日期;

/**
 * @author icboluo
 * @since 2023-03-21 22:17
 */
class N2224_转换时间需要的最少操作数 {
    public static void main(String[] args) {
        var cla = new N2224_转换时间需要的最少操作数();
        System.out.println(cla.convertTime("02:30", "04:35"));
        System.out.println(cla.convertTime("11:00", "11:01"));
    }

    /**
     * 将当前的时间转换为正确的时间，最少需要的操作数 FIXME ERROR
     * 也可以全部转换为分钟，大杂烩
     *
     * @param current
     * @param correct
     * @return
     */
    public int convertTime(String current, String correct) {
        if (current.equals(correct)) {
            return 0;
        }
        if (current.compareTo(correct) > 0) {
            return convertTime(correct, current);
        }
        int hour1 = Integer.parseInt(current.substring(0, 2));
        int hour2 = Integer.parseInt(correct.substring(0, 2));
        int minute1 = Integer.parseInt(current.substring(3));
        int minute2 = Integer.parseInt(correct.substring(3));

        // 小心这里分钟计算
        int hour;
        int minute;
        if (minute2 > minute1) {
            hour = hour2 - hour1;
            minute = minute2 - minute1;
        } else if (minute2 < minute1) {
            hour = hour2 - hour1 - 1;
            minute = 60 + minute2 - minute1;
        } else {
            hour = hour2 - hour1;
            minute = 0;
        }
        while (minute >= 15) {
            hour++;
            minute -= 15;
        }
        while (minute >= 5) {
            hour++;
            minute -= 5;
        }
        while (minute >= 1) {
            hour++;
            minute -= 1;
        }
        return hour;
    }
}
