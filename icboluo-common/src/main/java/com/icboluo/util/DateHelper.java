package com.icboluo.util;

import lombok.Data;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author icboluo
 */
@Data
@Slf4j
public class DateHelper {

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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(System.currentTimeMillis());
    }

    /**
     * @param pattern forExample:    yyyy-MM-dd HH:mm:ss
     * @return 2020-04-03 13:24:29
     */
    public static String getCurrentDateFormat(String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(System.currentTimeMillis());
    }

    /**
     * @param date Fri Apr 03 13:10:05 CST 2020
     * @return 2020-04-03 13:08:41
     */
    public static String dateFormat(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    /**
     * @param date    Fri Apr 03 13:10:05 CST 2020
     * @param pattern yyyy-MM-dd HH:mm:ss
     * @return 2020-04-03 13:08:41
     */
    public static String dateFormat(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
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
     * 将时间戳解析成日期对象
     *
     * @param l 1589450630
     * @return for example: Thu May 14 18:03:50 CST 2020
     */
    public static Date parseTimeStampToDate(Long l) {
        String substring = String.valueOf(l).substring(0, 10);
        return new Date(l);
    }
}
