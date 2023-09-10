package com.icboluo;

import com.icboluo.spring.accountqualifier.IAccountDao;
import com.icboluo.spring.accountxml.pojo.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationaQualifier.xml"})
public class AccountDao2Test {
    @Autowired
    @Qualifier("accountDao2")
    private IAccountDao accountDao;


    @Test
    public void queryAccountById() {
        Account account = accountDao.queryAccountById(1);
        System.out.println("account = " + account);
    }

    @Test
    public void queryAccountList() {
        List<Account> accounts = accountDao.queryAccountList();
        for (Account account : accounts) {
            System.out.println("account = " + account);
        }
    }

    @Test
    public void saveAccount() {
        Account account = new Account();
        account.setMoney(1777f);
        account.setName("ttt");
        accountDao.saveAccount(account);
    }

    @Test
    public void updateAccount() {
        Account account = new Account();
        account.setMoney(177f);
        account.setId(17);
        accountDao.updateAccount(account);
    }

    @Test
    public void deleteAccountById() {
        accountDao.deleteAccountById(17);
    }
}
