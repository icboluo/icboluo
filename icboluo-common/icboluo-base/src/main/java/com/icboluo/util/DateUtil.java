package com.icboluo.util;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

/**
 * @author icboluo
 */
@Data
@SuppressWarnings("unused")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateUtil {
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
     * YYYY_MM_DD_HH_MM_SS-----2023-09-19 12:12:12
     * ISO_LOCAL_DATE_TIME-----2023-09-19T12:12:12  2023-09-19T12:12:12.121  2023-09-19T12:12:12.123456789
     * ISO_DATE_TIME-----------2023-09-19T12:12:12.001Z
     */
    private static final List<DateTimeFormatter> DATE_TIME_FORMATTERS = Arrays.asList(Y_M_D_H_M_S,
            DateTimeFormatter.ISO_LOCAL_DATE_TIME, DateTimeFormatter.ISO_DATE_TIME);

    private static final List<DateTimeFormatter> DATE_FORMATTERS = Collections.singletonList(
            DateTimeFormatter.ISO_LOCAL_DATE);

    // login 接口修改为POST请求，前端需要一起修改

    // Object.assign(); // 对象合并
    // toRefs() // 将响应式对象转换为普通对象
    // toRef() // 将响应式对象的某个属性转换为普通对象
    // computed() // 计算属性，可以缓存计算结果（类似于备忘录模式）
    // watch() // 监听数据的变化
    // watchEffect() // 监听数据的变化，并执行相应的操作
    // 钩子函数
    //     <div ref="a"/>  let a=ref()  a相当于div的容器
//    public void aa() {
//        CompletableFuture<String> ecare = syncProjectData();
//        CompletableFuture<Void> trend = syncTrendData();
//        asyncJoin(ecare, trend);
//        return ecare.join();
//    }
//
//    private void asyncJoin(CompletableFuture<?>... taskArr) {
//        CompletableFuture.allOf(taskArr).handle((result, throwable) -> {
//            if (throwable == null) {
//                return null;
//            }
//            if (throwable.getCause() instanceof I18nException) {
//                throw new I18nException(((I18nException) throwable.getCause()).getCode(),
//                        throwable.getCause().getMessage());
//            } else {
//                log.error("", throwable);
//                throw new BusinessException();
//            }
//        }).join();
//    }
//
//    private CompletableFuture<String> syncProjectData() {
//        CompletableFuture<Void> cf1 = CompletableFuture.runAsync(
//                () -> voidFun());
//        CompletableFuture<String> cfTime = CompletableFuture.supplyAsync(
//                () -> stringRet());
//        return cfTime.thenCombine(CompletableFuture.allOf(cf1), (res, ignore) -> res);
//    }

    /**
     * ISO_LOCAL_DATE_TIME 这个支持的格式相当的多，秒以后的级别大部分都支持
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

    @Deprecated
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


    public static LocalDate firstDayOfMonth(LocalDate localDate) {
        return localDate.with(TemporalAdjusters.firstDayOfMonth());
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
        // 支持标准时间戳（毫秒值）转换为日期格式，此处亦可支持10位数秒级别的时间戳
        if (str.length() == 13) {
            try {
                return toDateTime(Long.parseLong(str));
            } catch (Exception ex) {

            }
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
        return Optional.ofNullable(allToDateTime(str)).map(LocalDateTime::toLocalDate).orElse(null);
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

    public static boolean between(LocalDate cur, LocalDate before, LocalDate after) {
        return !cur.isBefore(before) && !cur.isAfter(after);
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
