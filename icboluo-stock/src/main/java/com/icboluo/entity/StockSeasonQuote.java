package com.icboluo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * 赛季行情映射实体类* <p>将赛季内的交易日序号（tradeDay）映射到真实的历史交易日期（tradeDate），
 * 用于从 stock_daily 表中查询对应日期的行情数据。* 唯一约束：(season_id, trade_day)
 *
 * @author icboluo
 * @since 2026-07-22 22:47
 */
@Data
public class StockSeasonQuote implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 所属赛季ID
     */
    private Integer seasonId;
    /**
     * 交易日序号（从1开始）
     */
    private Integer tradeDay;
    /**
     * 对应的真实交易日期
     */
    private LocalDate tradeDate;

}
