package com.icboluo.controller;

import com.icboluo.service.StockInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("stockInfo")
public class StockInfoController {
    private final StockInfoService stockInfoService;
}
