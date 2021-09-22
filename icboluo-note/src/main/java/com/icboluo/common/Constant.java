package com.icboluo.common;

import lombok.Getter;
import lombok.ToString;

/**
 * @author icboluo
 */
@Getter
@ToString
public class Constant {

    /**
     * amendment 修正时间
     */
    public static final long GMT_AMENDMENT = -8 * 60 * 60 * 1000L - 60 * 60 * 1000L;
    /**
     * 一天时间间隔
     */
    public static final long DAY_TIME_INTERVAL = 24 * 60 * 60 * 1000L;
    /**
     * time 时间间隔
     */
    public static final Long TIME_NOTE_INTERVAL = DAY_TIME_INTERVAL + GMT_AMENDMENT;
    /**
     * week 时间间隔
     */
    public static final Long WEEK_TIME_INTERVAL = 7 * DAY_TIME_INTERVAL + GMT_AMENDMENT;
    /**
     * month 时间间隔
     */
    public static final Long MONTH_TIME_INTERVAL = 30 * DAY_TIME_INTERVAL + GMT_AMENDMENT;
    /**
     * year 时间间隔
     */
    public static final Long YEAR_TIME_INTERVAL = 365 * DAY_TIME_INTERVAL + GMT_AMENDMENT;
    /**
     * 次类型
     */
    public static final String TIME_TYPE = "time";
    /**
     * 周类型
     */
    public static final String WEEK_TYPE = "week";
    /**
     * 月类型
     */
    public static final String MONTH_TYPE = "month";
    /**
     * 年类型
     */
    public static final String YEAR_TYPE = "year";
    /**
     * 完成类型
     */
    public static final String FINISH_TYPE = "finish";
    /**
     * 次应该完成次数
     */
    public static final int TIME_FINISH_TIME = 8;
    /**
     * 周应该完成次数
     */
    public static final int WEEK_FINISH_TIME = 3;
    /**
     * 月应该完成次数
     */
    public static final int MONTH_FINISH_TIME = 2;
    /**
     * 年应该完成次数
     */
    public static final int YEAR_FINISH_TIME = 1;
    /**
     * 未完成
     */
    public static final int NOT_FINISH = 1;
    /**
     * 完成
     */
    public static final int FINISH = 2;
    /**
     * 只阅读
     */
    public static final int ONLY_READ = 3;
    /**
     * time 完成失败之后剩余完成次数
     */
    public static final int TIME_NOT_FINISH_TIME = 5;
    /**
     * week 完成失败之后剩余完成次数
     */
    public static final int WEEK_NOT_FINISH_TIME = 0;
    /**
     * month 完成失败之后剩余完成次数
     */
    public static final int MONTH_NOT_FINISH_TIME = 0;
    /**
     * year 完成失败之后剩余完成次数
     */
    public static final int YEAR_NOT_FINISH_TIME = 0;

    public static final String UNIT = "unit";
}
