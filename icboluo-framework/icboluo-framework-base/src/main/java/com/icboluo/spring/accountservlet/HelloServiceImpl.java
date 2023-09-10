package com.icboluo.spring.accountservlet;

public class HelloServiceImpl implements IHelloService {
    private IHelloDao helloDao;

    public void setHelloDao(IHelloDao helloDao) {
        this.helloDao = helloDao;
    }

    @Override
    public void sayHello() {
        helloDao.sayHello();
    }


}
