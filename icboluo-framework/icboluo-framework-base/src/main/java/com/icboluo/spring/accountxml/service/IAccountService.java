package com.icboluo.spring.accountxml.service;


import com.icboluo.spring.accountxml.pojo.Account;

import java.util.List;

public interface IAccountService {
    /**
     * 保存账户
     * @param account
     */
    void saveAccount(Account account);

    /**
     * 更新账户
     * @param account
     */
    void updateAccount(Account account);

    /**
     * 删除账户
     * @param accountId
     */
    void deleteAccountById(Integer accountId);

    /**
     * 根据id查询账户
     * @param accountId
     * @return
     */
    Account queryAccountById(Integer accountId);

    /**
     * 查询所有账户
     * @return
     */
    List<Account> queryAccountList();
}
