package com.icboluo.common;

/**
 * 基金指标
 *
 * @author icboluo
 * @since 2024-05-15 20:27
 */
public enum FundMetricEnum {
    /**
     * 10个交易日以前的数据
     */
    @Desc("10个交易日以前的数据")
    TEN_TRADING_AGO,

    @Desc("最近一个月")
    ONE_MONTH_AGO,

    @Desc("最近一年")
    ONE_YEAR_AGO,

    @Desc("最近二年")
    TWO_YEAR_AGO,

    @Desc("最近三年")
    THREE_YEAR_AGO,

    @Desc("当收益率为当前的时候，5天内涨幅（以近一年数据为准）")
    ONE_WEEK_RISE_5,

    @Desc("当收益率为当前的时候，10天内涨幅（以近一年数据为准）")
    ONE_WEEK_RISE_10,

    @Desc("当收益率为当前的时候，20天内涨幅（以近一年数据为准）")
    ONE_WEEK_RISE_20,

    @Desc("当收益率为当前的时候（取2天数据做比较），5天内涨幅（以近一年数据为准）")
    RISE_COMPARE_TWO_DAY_5,

    @Desc("去年最近一个月平均涨幅（从当前往后推一个月）")
    RISE_RECENT_THIS_MONTH_LAST_YEAR,

    @Desc("前年最近一个月平均涨幅（从当前往后推一个月）")
    RISE_RECENT_THIS_MONTH_LAST_YEAR_2,
    ;
}
