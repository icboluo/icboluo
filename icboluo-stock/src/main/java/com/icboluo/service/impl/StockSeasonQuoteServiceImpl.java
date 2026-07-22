package com.icboluo.service.impl;

import com.icboluo.mapper.StockSeasonQuoteMapper;
import com.icboluo.service.StockSeasonQuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockSeasonQuoteServiceImpl implements StockSeasonQuoteService {
    private final StockSeasonQuoteMapper stockSeasonQuoteMapper;
}
