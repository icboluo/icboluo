package com.icboluo;

import com.icboluo.spring.accountxml.pojo.Account;
import com.icboluo.spring.accountxml.service.IAccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;


class AccountXmlTest1 {
    private IAccountService accountService;

    Account account = new Account();

    @BeforeEach
    public void setUp() throws Exception {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationXml.xml");
        accountService = (IAccountService) ac.getBean("accountService");
    }

    @Test
    void saveAccount() {
        account.setName("777");
        account.setMoney(123456f);
        accountService.saveAccount(account);
    }

    @Test
    void updateAccount() {
        account.setMoney(88888f);
        account.setId(11);
        accountService.updateAccount(account);
    }

    @Test
    void deleteAccountById() {
        accountService.deleteAccountById(11);
    }

    @Test
    void queryAccountById() {
        Account account = accountService.queryAccountById(10);
        System.out.println("account = " + account);
    }

    @Test
    void queryAccountList() {
        List<Account> accounts = accountService.queryAccountList();
        for (Account account1 : accounts) {
            System.out.println("account1 = " + account1);
        }
    }
}
