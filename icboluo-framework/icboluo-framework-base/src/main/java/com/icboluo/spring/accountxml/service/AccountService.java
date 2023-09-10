package com.icboluo.spring.accountxml.service;


import com.icboluo.spring.accountxml.mapper.IAccountMapper;
import com.icboluo.spring.accountxml.pojo.Account;

import java.util.List;

public class AccountService implements IAccountService {

    private IAccountMapper accountDao;

    public void setAccountDao(IAccountMapper accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public void saveAccount(Account account) {
        accountDao.saveAccount(account);
    }

    @Override
    public void updateAccount(Account account) {
        accountDao.updateAccount(account);
    }

    @Override
    public void deleteAccountById(Integer accountId) {
        accountDao.deleteAccountById(accountId);
    }

    @Override
    public Account queryAccountById(Integer accountId) {
        return accountDao.queryAccountById(accountId);
    }

    @Override
    public List<Account> queryAccountList() {
        return accountDao.queryAccountList();
    }
}
