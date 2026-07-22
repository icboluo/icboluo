package com.icboluo.controller;

import com.icboluo.service.StockDailyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("stockDaily")
public class StockDailyController  {
    private final StockDailyService stockDailyService;
}
