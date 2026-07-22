package com.icboluo.controller;

import com.icboluo.service.StockAccountService;
import com.icboluo.service.StockSeasonQuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("stockSeasonQuote")
public class StockSeasonQuoteController  {

    private final StockSeasonQuoteService stockSeasonQuoteService;
}
