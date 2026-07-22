package com.icboluo.service.impl;

import com.icboluo.mapper.StockDailyMapper;
import com.icboluo.service.StockDailyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockDailyServiceImpl implements StockDailyService {
    private final StockDailyMapper stockDailyMapper;
}
