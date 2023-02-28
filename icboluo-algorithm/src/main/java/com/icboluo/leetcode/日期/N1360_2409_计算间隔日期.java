package com.icboluo.leetcode.日期;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * @author icboluo
 * @since 2023-01-07 23:43
 */
class N1360_2409_计算间隔日期 {
    public static void main(String[] args) {
        var cla = new N1360_2409_计算间隔日期();
        System.out.println(cla.countDaysTogether2("08-06", "12-08", "02-04", "09-01"));
    }

    public int daysBetweenDates(String date1, String date2) {
        LocalDate parse1 = LocalDate.parse(date1, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate parse2 = LocalDate.parse(date2, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return Math.abs((int) ChronoUnit.DAYS.between(parse1, parse2));
    }

    /**
     * 2409 计算一起度过的日子，日期交集 ERROR,这个题只有月-日，没有年
     * 日期交集
     *
     * @param arriveAlice
     * @param leaveAlice
     * @param arriveBob
     * @param leaveBob
     * @return
     */
    public int countDaysTogether1(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
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

    private int[] months = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public int countDaysTogether2(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int[] arr1 = {val(arriveAlice), val(leaveAlice)};
        int[] arr2 = {val(arriveBob), val(leaveBob)};
        if (arr1[0] > arr2[1] || arr2[0] > arr1[1]) {
            return 0;
        }
        int start = Math.max(arr1[0], arr2[0]);
        int end = Math.min(arr1[1], arr2[1]);
        return end - start + 1;
    }

    private int val(String date) {
        int month = (date.charAt(0) - '0') * 10 + (date.charAt(1) - '0');
        int day = (date.charAt(3) - '0') * 10 + (date.charAt(4) - '0');
        int sum = 0;
        // 减1代表不取当前月
        for (int i = 0; i < month - 1; i++) {
            sum += months[i];
        }
        return sum + day;
    }
}
