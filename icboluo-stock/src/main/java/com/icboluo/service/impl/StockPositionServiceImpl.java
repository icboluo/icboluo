package com.icboluo.service.impl;

import com.icboluo.mapper.StockPositionMapper;
import com.icboluo.service.StockPositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockPositionServiceImpl implements StockPositionService {
    private final StockPositionMapper stockPositionMapper;
}
