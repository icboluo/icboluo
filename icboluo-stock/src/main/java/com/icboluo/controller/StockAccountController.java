package com.icboluo.controller;

import com.icboluo.service.StockAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("stockAccount")
public class StockAccountController {

    private final StockAccountService stockAccountService;
}
