package com.icboluo.spring.accountanno.service;

public interface IAccountService {
    /**
     * 模拟保存
     */
    void saveAccount();

    /**
     * 模拟更新
     * @param i
     */
    void updateAccount(int i);

    /**
     * 模拟删除
     * @return
     */
    int deleteAccount();
}
