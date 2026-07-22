package com.icboluo.controller;

import com.icboluo.service.StockTradeRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("stockTradeRecord")
public class StockTradeRecordController {
    private final StockTradeRecordService stockTradeRecordService;
}
