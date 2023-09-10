package com.icboluo.spring.accountxml.utils;


import com.icboluo.spring.accountxml.service.IAccountService;
import lombok.Setter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Setter
public class BeanFactory {
    private IAccountService accountService;
    private Logger logger;

    public IAccountService getAccountServiceProxy() {
       return (IAccountService) Proxy.newProxyInstance(accountService.getClass().getClassLoader(), accountService.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                logger.printLog();
                Object invoke = method.invoke(accountService, args);
                return invoke;
            }
        });
    }
}
