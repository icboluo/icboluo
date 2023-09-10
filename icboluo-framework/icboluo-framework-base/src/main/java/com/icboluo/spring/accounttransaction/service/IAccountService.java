package com.icboluo.spring.accounttransaction.service;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface IAccountService {
    void transfer(String sourceName, String targetName, Float money);
}
