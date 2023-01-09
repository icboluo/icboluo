package com.icboluo.leetcode.after_1300;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * @author icboluo
 * @since 2023-01-07 23:43
 */
public class N1360_2409_计算间隔日期 {
    public int daysBetweenDates(String date1, String date2) {
        LocalDate parse1 = LocalDate.parse(date1, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate parse2 = LocalDate.parse(date2, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return Math.abs((int) ChronoUnit.DAYS.between(parse1, parse2));
    }

    /**
     * 2409 计算一起度过的日志，日期交集 FIXME ERROR,这个题只有月-日，没有年
     *
     * @param arriveAlice
     * @param leaveAlice
     * @param arriveBob
     * @param leaveBob
     * @return
     */
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        LocalDate aa = LocalDate.parse(arriveAlice, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate al = LocalDate.parse(leaveAlice, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate ba = LocalDate.parse(arriveBob, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate bl = LocalDate.parse(leaveBob, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        if (aa.isBefore(ba) && al.isAfter(bl)) {
            return (int) ChronoUnit.DAYS.between(ba, bl);
        }

        if (ba.isBefore(aa) && bl.isAfter(al)) {
            return (int) ChronoUnit.DAYS.between(aa, al);
        }
        return -1;
    }
}
