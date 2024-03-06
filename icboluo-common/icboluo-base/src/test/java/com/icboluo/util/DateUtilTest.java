package com.icboluo.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class DateUtilTest {

    @Test
    void test1() {
        LocalDate localDate1 = LocalDate.of(1991, 10, 12);
        LocalDate localDate2 = LocalDate.ofYearDay(1991, localDate1.getDayOfYear());
        // 参数为距离1970-01-01的天数
        LocalDate localDate3 = LocalDate.ofEpochDay(localDate1.toEpochDay());
        LocalDate parse = LocalDate.parse("1991-10-12");
        LocalDate localDate4 = LocalDate.parse("19911012", DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    @Test
    void test2() {
        // 08:20
        LocalTime of = LocalTime.of(8, 20);
        // 08:20:30
        LocalTime of1 = LocalTime.of(8, 20, 30);
        // 08:20:30.000000040
        LocalTime of2 = LocalTime.of(8, 20, 30, 40);
        // 参数为距离当天零时的秒数 08:20:30
        LocalTime localTime = LocalTime.ofSecondOfDay(of2.toSecondOfDay());
        // 08:20:30.000000040
        LocalTime localTime1 = LocalTime.ofNanoOfDay(of2.toNanoOfDay());
        // 08:20:30
        LocalTime parse1 = LocalTime.parse("08:20:30");
        // 08:20:30
        LocalTime hHmmss = LocalTime.parse("082030", DateTimeFormatter.ofPattern("HHmmss"));

        // 1991-10-12T08:20:30
        LocalDateTime of3 = LocalDateTime.of(1991, 10, 12, 8, 20, 30);
        // second exception
        // LocalDateTime of4 = LocalDateTime.of(1991, Month.OCTOBER, 8, 20, 30, 150);
        // 1991-10-12T08:20:30
        LocalDateTime parse2 = LocalDateTime.parse("1991-10-12 08:20:30", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        of3.minusDays(3);
    }

    @Test
    void allToDate() {
        assertEquals(LocalDate.of(2023, 11, 5), DateUtil.allToDate("2023-11-05"));
        assertEquals(LocalDate.of(2023, 11, 5), DateUtil.allToDate("2023-11-05 12:12:12"));
        assertEquals(LocalDate.of(2023, 11, 5), DateUtil.allToDate("2023-11-05T13:13:13"));
        assertEquals(LocalDate.of(2023, 11, 5), DateUtil.allToDate("2023-11-05T14:14:14.141"));
        assertEquals(LocalDate.of(2023, 11, 5), DateUtil.allToDate("2023-11-05T15:15:15.123456789"));
    }
}
