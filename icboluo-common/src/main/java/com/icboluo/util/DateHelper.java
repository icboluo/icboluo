package com.icboluo.util;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.List;

/**
 * @author icboluo
 */
@Data
@Slf4j
public class DateHelper {

    public static final ThreadLocal<List<LocalDateTime>> USER_CONTEXT = new ThreadLocal<>();

    /**
     * 经过一年的时间戳增加值
     */
    private final Long year = 365 * 24 * 60 * 60 * 1000L;
    /**
     * 经过一月的时间戳增加值
     */
    private final Long month = 30 * 24 * 60 * 60 * 1000L;
    /**
     * 经过一周的时间戳增加值
     */
    private final Long week = 7 * 24 * 60 * 60 * 1000L;
    /**
     * 经过一天的时间戳增加值
     */
    private final Long day = 24 * 60 * 60 * 1000L;


    /**
     * @return 2020-04-03 13:24:29
     */
    public static String getCurrentDateFormat() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * @param pattern forExample:    yyyy-MM-dd HH:mm:ss
     * @return 2020-04-03 13:24:29
     */
    public static String getCurrentDateFormat(String pattern) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * @param dateTime 2020-10-21T21:15:33.464696200
     * @return 2020-04-03 13:08:41
     */
    public static String dateFormat(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * @param dateTime 2020-10-21T21:15:33.464696200
     * @param pattern  yyyy-MM-dd HH:mm:ss
     * @return 2020-04-03 13:08:41
     */
    public static String dateFormat(LocalDateTime dateTime, String pattern) {
        return dateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * @param timeStamp 1589450630
     * @return for example: 2020-10-21T21:42:13
     */
    public static LocalDateTime parseTimeStampToDate(Long timeStamp) {
        return LocalDateTime.ofEpochSecond(timeStamp / 1000, 0, ZoneOffset.ofHours(8));
    }

    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDateTime dateToLocalDateTime(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    /**
     * 这天的最后一秒，在数据库中因为数据库精度不够，会进一
     *
     * @param localDate 天数
     * @return 这天的最后时间
     */
    public static LocalDateTime finalTime(LocalDate localDate) {
        LocalTime maxTime = LocalTime.MAX;
        return LocalDateTime.of(localDate, maxTime);
    }

    public static LocalDateTime firstTime(LocalDate localDate) {
        LocalTime minTime = LocalTime.MIN;
        return LocalDateTime.of(localDate, minTime);
    }
    /**
     * 本月的最后一天
     *
     * @param localDate 当前日期
     * @return 本月的最后一天队应的日期
     */
    public static LocalDate lastDayOfMonth(LocalDate localDate) {
        return localDate.with(TemporalAdjusters.lastDayOfMonth());
    }
}
