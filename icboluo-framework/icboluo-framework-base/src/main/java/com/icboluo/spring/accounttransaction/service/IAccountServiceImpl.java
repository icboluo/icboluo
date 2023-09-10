package com.icboluo.spring.accounttransaction.service;

import com.icboluo.spring.accountxml.pojo.Account;
import com.icboluo.spring.accounttransaction.dao.IAccoutDao;
import org.springframework.stereotype.Service;

@Service("accountService")
public class IAccountServiceImpl implements IAccountService {
    private IAccoutDao accoutDao;

    public void setAccoutDao(IAccoutDao accoutDao) {
        this.accoutDao = accoutDao;
    }

    @Override
    public void transfer(String sourceName, String targetName, Float money) {
        Account source = accoutDao.queryAccountByName(sourceName);
        Account target = accoutDao.queryAccountByName(targetName);
        source.setMoney(source.getMoney() - money);
        target.setMoney(target.getMoney() + money);
        accoutDao.updateAccount(source);
        int x = 1 / 0;
        accoutDao.updateAccount(target);
    }
}
