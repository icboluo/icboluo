package com.icboluo.controller;

import com.icboluo.service.StockSeasonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("stockSeason")
public class StockSeasonController {
    private final StockSeasonService stockSeasonService;
}
