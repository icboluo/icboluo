package com.icboluo.util;

import lombok.Data;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author icboluo
 */
@Data
@Slf4j
public class DateHelper {

    public static final ThreadLocal<List<LocalDateTime>> userContext = new ThreadLocal<>();

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
     * @param dateTime    2020-10-21T21:15:33.464696200
     * @param pattern yyyy-MM-dd HH:mm:ss
     * @return 2020-04-03 13:08:41
     */
    public static String dateFormat(LocalDateTime dateTime, String pattern) {
        return dateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 将时间戳解析成秒并打印，
     * for example 整个算法一共经过2.98s
     *
     * @param timeStamp 时间戳
     */
    public static void parseTimeStampToSecond(@NonNull Long timeStamp) {
        String msg = "整个算法一共经过";
        if (timeStamp < 10 && timeStamp > 0) {
            log.debug(msg + "{}毫秒", timeStamp);
            return;
        }
        long l = timeStamp / 10;
        String s = String.valueOf(l);
        if (s.length() == 1) {
            log.debug(msg + "0.0{}s", s);
            return;
        }
        String pre = s.substring(0, s.length() - 2);
        pre = "".equals(pre) ? "0" : pre;
        String time = pre + "." + s.substring(s.length() - 2);
        log.debug(msg + "{}s", time);
    }

    /**
     * @param timeStamp 1589450630
     * @return for example: 2020-10-21T21:42:13
     */
    public static LocalDateTime parseTimeStampToDate(Long timeStamp) {
        return LocalDateTime.ofEpochSecond(timeStamp / 1000, 0, ZoneOffset.ofHours(8));
    }
    public void dd() {

    }
}
