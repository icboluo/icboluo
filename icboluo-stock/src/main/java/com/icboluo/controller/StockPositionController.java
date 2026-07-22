package com.icboluo.controller;

import com.icboluo.service.StockPositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("stockPosition")
public class StockPositionController  {
    private final StockPositionService stockPositionService;
}
