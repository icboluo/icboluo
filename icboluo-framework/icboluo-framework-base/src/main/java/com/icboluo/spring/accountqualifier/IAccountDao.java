package com.icboluo.spring.accountqualifier;


import com.icboluo.spring.accountxml.pojo.Account;

import java.util.List;

public interface IAccountDao {
    Account queryAccountById(Integer id);

    List<Account> queryAccountList();

    void saveAccount(Account account);

    void updateAccount(Account account);

    void deleteAccountById(Integer id);
}
