package com.icboluo;

import com.icboluo.spring.accounttransaction.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationTransaction.xml"})
public class AccountTransactionTest {
    @Autowired
    private IAccountService accountService;
    @Test
    public void transfer() {
        accountService.transfer("aaa", "bbb", 100f);
    }
}
