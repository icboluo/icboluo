package com.icboluo.leetcode.日期;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatter;

/**
 * @author icboluo
 * @since 2022-12-28 22:32
 */
class N1154_day_of_year {
    public int dayOfYear1(String date) {
        LocalDate parse = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return parse.getDayOfYear();
    }

    public int dayOfYear2(String date) {
        String[] split = date.split("-");
        long year = Long.parseLong(split[0]);
        int month = Integer.parseInt(split[1]);
        boolean leapYear = IsoChronology.INSTANCE.isLeapYear(year);
        int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int count = 0;
        for (int i = 0; i < month - 1; i++) {
            count += days[i];
        }
        if (leapYear && month > 2) {
            count++;
        }
        return count + Integer.parseInt(split[2]);
    }

    /**
     * 1185 day of week
     * 我们可以知道今天是星期几，计算年份月份，日期差即可，算法本身是麻烦的，但是并非困难
     *
     * @param day
     * @param month
     * @param year
     * @return
     */
    public String dayOfTheWeek(int day, int month, int year) {
        LocalDate localDate = LocalDate.of(year, month, day);
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        return dayOfWeek.name().substring(0, 1) + dayOfWeek.name().substring(1).toLowerCase();
    }
}
