package com.icboluo.service.impl;

import com.icboluo.mapper.StockSeasonMapper;
import com.icboluo.service.StockSeasonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockSeasonServiceImpl implements StockSeasonService {
    private final StockSeasonMapper stockSeasonMapper;
}
