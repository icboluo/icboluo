package com.icboluo.util;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author icboluo
 */
@Data
@SuppressWarnings("unused")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateHelper {
    /**
     * 经过一年的时间戳增加值
     */
    private static final Long YEAR = 365 * 24 * 60 * 60 * 1000L;
    /**
     * 经过一月的时间戳增加值
     */
    private static final Long MONTH = 30 * 24 * 60 * 60 * 1000L;
    /**
     * 经过一周的时间戳增加值
     */
    private static final Long WEEK = 7 * 24 * 60 * 60 * 1000L;
    /**
     * 经过一天的时间戳增加值
     */
    private static final Long DAY = 24 * 60 * 60 * 1000L;

    private static final Long HOUR = 60 * 60 * 1000L;
    private static final Long MIN = 60 * 1000L;

    private static DateTimeFormatter Y_M_D_H_M_S = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 此formatters需要修改默认支持的数据类型
     */
    private static List<DateTimeFormatter> dateTimeFormatters = Arrays.asList(Y_M_D_H_M_S, DateTimeFormatter.ISO_LOCAL_DATE_TIME);

    private static List<DateTimeFormatter> dateFormatters = Arrays.asList(DateTimeFormatter.ISO_LOCAL_DATE);

    /**
     * 获取当前的标准日期
     *
     * @return 2020-04-03 13:24:29
     */
    public static String getCurrentDateFormat() {
        return LocalDateTime.now().format(Y_M_D_H_M_S);
    }

    /**
     * 获取当前的日期
     *
     * @param pattern forExample:    yyyy-MM-dd HH:mm:ss
     * @return 2020-04-03 13:24:29
     */
    public static String getCurrentDateFormat(String pattern) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 日期格式化为标准字符串
     *
     * @param dateTime 2020-10-21T21:15:33.464696200
     * @return 2020-04-03 13:08:41
     */
    public static String dateFormat(LocalDateTime dateTime) {
        return dateTime.format(Y_M_D_H_M_S);
    }

    /**
     * 日期格式化成字符串
     *
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
    public static LocalDateTime toDateTime(Long timeStamp) {
        Instant instant = Instant.ofEpochMilli(timeStamp);
        ZoneId zoneId = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zoneId);
    }

    public static long toLong(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zoneId).toInstant();
        return instant.toEpochMilli();
    }

    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDateTime dateToLocalDateTime(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public static LocalDateTime firstTime(LocalDate localDate) {
        return localDate.atStartOfDay();
    }

    /**
     * 这天的最后一秒，在数据库中因为数据库精度不够，会进一
     *
     * @param localDate 天数
     * @return 这天的最后时间
     */
    public static LocalDateTime finalTime(LocalDate localDate) {
        return LocalDateTime.of(localDate, LocalTime.MAX);
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

    public static LocalDateTime toDateTime(String str) {
        return LocalDateTime.parse(str, Y_M_D_H_M_S);
    }

    /**
     * 把多种时间类型转换为LocalDateTime类型
     *
     * @param str 字符串时间
     * @return 日期
     */
    public static LocalDateTime allToDateTime(String str) {
        if (str == null) {
            return null;
        }
        for (DateTimeFormatter formatter : dateTimeFormatters) {
            try {
                return LocalDateTime.parse(str, formatter);
            } catch (Exception exception) {
                // do nothing
            }
        }
        return null;
    }

    public static LocalDate allToDate(String str) {
        if (str == null) {
            return null;
        }
        for (DateTimeFormatter formatter : dateFormatters) {
            try {
                return LocalDate.parse(str, formatter);
            } catch (Exception exception) {
                // do nothing
            }
        }
        LocalDateTime localDateTime = allToDateTime(str);
        return localDateTime == null ? null : localDateTime.toLocalDate();
    }


    /**
     * 转换成年月字符串
     *
     * @param localDate 日期
     * @return 年月字符串
     */
    public static String toYearMonth(LocalDate localDate) {
        int year = localDate.getYear();
        int month = localDate.getMonthValue();
        return year + "-" + month;
    }

    /**
     * <p>计算分钟时间间隔，取2位小数，计算过程取毫秒值
     * <p>请自行维护开始时间小于结束时间
     *
     * @param start 开始日期
     * @param end   结束日期
     * @return 时间间隔
     */
    public static BigDecimal betweenMin(LocalDateTime start, LocalDateTime end) {
        Duration duration = Duration.between(start, end);
        long min = duration.toMinutes();
        long mills = duration.toMillis();
        // 毫秒占比+分钟数
        return MathUtil.divide(mills - min * MIN, MIN).add(BigDecimal.valueOf(min));
    }

    /**
     * 计算小时时间间隔，取2位小数，计算过程取毫秒值
     *
     * @param start 开始日期
     * @param end   结束日期
     * @return 时间间隔
     */
    public static BigDecimal betweenHours(LocalDateTime start, LocalDateTime end) {
        Duration duration = Duration.between(start, end);
        long hours = duration.toHours();
        long mills = duration.toMillis();
        // 毫秒占比+分钟数
        return MathUtil.divide(mills - hours * HOUR, HOUR).add(BigDecimal.valueOf(hours));
    }

    /**
     * 计算天时间间隔，取2位小数，计算过程取毫秒值
     *
     * @param start 开始日期
     * @param end   结束日期
     * @return 时间间隔
     */
    public static BigDecimal betweenDay(LocalDateTime start, LocalDateTime end) {
        Duration duration = Duration.between(start, end);
        long min = duration.toMinutes();
        long mills = duration.toMillis();
        // 毫秒占比+分钟数
        return MathUtil.divide(mills - min * DAY, DAY).add(BigDecimal.valueOf(min));
    }
}
