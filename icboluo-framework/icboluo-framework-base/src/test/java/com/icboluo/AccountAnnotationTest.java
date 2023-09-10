package com.icboluo;

import com.icboluo.spring.accountanno.service.IAccountService;
import com.icboluo.spring.accountanno.springConfiguation.SpringConfiguation;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AccountAnnotationTest {
    private IAccountService accountService;

    @Before
    public void setUp() throws Exception {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguation.class);
        accountService = (IAccountService) ac.getBean("accountService");
    }

    @Test
    public void saveAccount() {
        accountService.saveAccount();
    }

    @Test
    public void updateAccount() {
        accountService.updateAccount(1);
    }

    @Test
    public void deleteAccount() {
        accountService.deleteAccount();
    }

}
