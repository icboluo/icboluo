package com.icboluo.controller;

import com.icboluo.object.co.AccountQueryCo;
import com.icboluo.object.co.SeasonQueryCo;
import com.icboluo.object.co.StockChartCo;
import com.icboluo.object.vo.QuoteVo;
import com.icboluo.object.vo.StockChartVo;
import com.icboluo.service.StockQuoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 股票行情控制器
 */
@RestController
@RequestMapping("stockQuote")
@RequiredArgsConstructor
public class StockQuoteController {
    private final StockQuoteService stockQuoteService;

    /**
     * 查询当前交易日行情
     */
    @PostMapping("current")
    public List<QuoteVo> current(@RequestBody @Valid SeasonQueryCo co) {
        return stockQuoteService.getCurrentQuotes(co.getSeasonId());
    }

    /**
     * 查询股票走势图数据（含买卖标记）
     */
    @PostMapping("chart")
    public StockChartVo chart(@RequestBody @Valid StockChartCo co) {
        return stockQuoteService.getStockChart(co.getSeasonId(), co.getStockCode(), co.getPlayerName());
    }

    /**
     * 查询某玩家所有交易股票的走势图数据
     */
    @PostMapping("playerCharts")
    public List<StockChartVo> playerCharts(@RequestBody @Valid AccountQueryCo co) {
        return stockQuoteService.getPlayerCharts(co.getSeasonId(), co.getPlayerName());
    }

    /**
     * 查询某玩家交易过的股票代码列表
     */
    @PostMapping("tradedStockCodes")
    public List<String> tradedStockCodes(@RequestBody @Valid AccountQueryCo co) {
        return stockQuoteService.getTradedStockCodes(co.getSeasonId(), co.getPlayerName());
    }
}
