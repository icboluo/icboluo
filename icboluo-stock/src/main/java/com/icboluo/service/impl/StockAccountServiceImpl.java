package com.icboluo.service.impl;

import com.icboluo.mapper.StockAccountMapper;
import com.icboluo.service.StockAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockAccountServiceImpl implements StockAccountService {
    private final StockAccountMapper stockAccountMapper;
}
