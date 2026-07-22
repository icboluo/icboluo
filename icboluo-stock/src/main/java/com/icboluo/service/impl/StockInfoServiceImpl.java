package com.icboluo.service.impl;

import com.icboluo.mapper.StockInfoMapper;
import com.icboluo.service.StockInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockInfoServiceImpl implements StockInfoService {
    private final StockInfoMapper stockInfoMapper;
}
