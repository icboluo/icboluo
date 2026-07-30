package com.icboluo.service;

import com.icboluo.object.vo.QuoteVo;
import com.icboluo.object.vo.StockChartVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 股票行情服务接口
 */
interface StockQuoteService {
    /**
     * 查询当前交易日行情
     *
     * @param seasonId 赛季ID
     * @return 行情列表
     */
    List<QuoteVo> getCurrentQuotes(Integer seasonId);

    /**
     * CSV导入行情数据
     *
     * @param file CSV文件
     * @return 导入结果
     */
    String importFromCsv(MultipartFile file);

    /**
     * 获取股票走势图数据（含买卖标记）
     *
     * @param seasonId   赛季ID
     * @param stockCode  股票代码
     * @param playerName 玩家名称
     * @return 走势图数据
     */
    StockChartVo getStockChart(Integer seasonId, String stockCode, String playerName);

    /**
     * 获取某玩家在某赛季所有交易股票的走势图数据
     *
     * @param seasonId   赛季ID
     * @param playerName 玩家名称
     * @return 多只股票走势图数据列表
     */
    List<StockChartVo> getPlayerCharts(Integer seasonId, String playerName);

    /**
     * 获取某玩家在某赛季交易过的股票代码列表
     *
     * @param seasonId   赛季ID
     * @param playerName 玩家名称
     * @return 股票代码列表
     */
    List<String> getTradedStockCodes(Integer seasonId, String playerName);
}
