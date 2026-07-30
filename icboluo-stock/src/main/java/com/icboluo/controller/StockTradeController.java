package com.icboluo.controller;

import com.github.pagehelper.PageInfo;
import com.icboluo.object.co.TradeCo;
import com.icboluo.object.co.TradeRecordQueryCo;
import com.icboluo.object.vo.TradeRecordVo;
import com.icboluo.service.StockTradeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 股票交易控制器
 */
@RestController
@RequestMapping("stockTrade")
@RequiredArgsConstructor
public class StockTradeController {
    private final StockTradeService stockTradeService;

    /**
     * 买入股票
     */
    @PostMapping("buy")
    public TradeRecordVo buy(@RequestBody @Valid TradeCo co) {
        return stockTradeService.buy(co, co.getPlayerName());
    }

    /**
     * 卖出股票
     */
    @PostMapping("sell")
    public TradeRecordVo sell(@RequestBody @Valid TradeCo co) {
        return stockTradeService.sell(co, co.getPlayerName());
    }

    /**
     * 分页查询交易记录
     */
    @PostMapping("records")
    public PageInfo<TradeRecordVo> records(@RequestBody @Valid TradeRecordQueryCo co) {
        return stockTradeService.getTradeRecords(co.getSeasonId(), co.getPlayerName(), co.getPageNum(), co.getPageSize());
    }
}
