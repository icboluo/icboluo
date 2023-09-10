package com.icboluo.spring.accounttransaction.dao;


import com.icboluo.spring.accountxml.pojo.Account;

public interface IAccoutDao {

    Account queryAccountByName(String sourceName);

    void updateAccount(Account account);
}
