package com.icboluo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.icboluo.entity.StockDaily;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

public interface StockDailyMapper extends BaseMapper<StockDaily> {

    /**
     * 统计给定交易日期集合内、有行情记录的去重股票数量。
     *
     * @param dates 交易日期列表
     * @return 去重后的股票代码数量
     */
    int countDistinctStockCodeByTradeDates(@Param("dates") List<LocalDate> dates);
}
