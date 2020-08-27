package com.icboluo.ee.tomcat.loginDemo.service;


import com.icboluo.dataobject.User;
import com.icboluo.ee.tomcat.loginDemo.dao.UserDao;

public class UserService {
    //处理登录业务
    public User login(User user) {
        UserDao userDao = new UserDao();
        return userDao.queryByUsernameAndPassword(user);
    }
}
