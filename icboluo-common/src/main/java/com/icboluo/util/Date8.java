package com.icboluo.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author icboluo
 * @date 2020/10/13 20:13
 */
public class Date8 {
    public static void main(String[] args) {
//        1991-10-12
        LocalDate localDate = LocalDate.of(1991, 10, 12);
        LocalDate localDate1 = LocalDate.ofYearDay(1991, localDate.getDayOfYear());
        LocalDate localDate2 = LocalDate.ofEpochDay(localDate.toEpochDay());//参数为距离1970-01-01的天数
        LocalDate parse = LocalDate.parse("1991-10-12");
        LocalDate yyyyMMdd = LocalDate.parse("19911012", DateTimeFormatter.ofPattern("yyyyMMdd"));

//        08:20
        LocalTime of = LocalTime.of(8, 20);
//        08:20:30
        LocalTime of1 = LocalTime.of(8, 20, 30);
//        08:20:30.000000040
        LocalTime of2 = LocalTime.of(8, 20, 30, 40);
        //参数为距离当天零时的秒数 08:20:30
        LocalTime localTime = LocalTime.ofSecondOfDay(of2.toSecondOfDay());
//        08:20:30.000000040
        LocalTime localTime1 = LocalTime.ofNanoOfDay(of2.toNanoOfDay());
//        08:20:30
        LocalTime parse1 = LocalTime.parse("08:20:30");
//        08:20:30
        LocalTime hHmmss = LocalTime.parse("082030", DateTimeFormatter.ofPattern("HHmmss"));

//        1991-10-12T08:20:30
        LocalDateTime of3 = LocalDateTime.of(1991, 10, 12, 8, 20, 30);
//        second exception
//        LocalDateTime of4 = LocalDateTime.of(1991, Month.OCTOBER, 8, 20, 30, 150);
//        1991-10-12T08:20:30
        LocalDateTime parse2 = LocalDateTime.parse("1991-10-12 08:20:30", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        parse.minusDays(3);
    }
}
