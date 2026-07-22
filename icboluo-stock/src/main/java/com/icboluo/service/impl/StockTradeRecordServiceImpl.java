package com.icboluo.service.impl;

import com.icboluo.mapper.StockTradeRecordMapper;
import com.icboluo.service.StockTradeRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockTradeRecordServiceImpl implements StockTradeRecordService {
    private final StockTradeRecordMapper stockTradeRecordMapper;
}
