package com.icboluo.z2.service;


import com.icboluo.z2.bean.User;
import com.icboluo.z2.dao.UserDao;
import com.icboluo.z2.dao.UserDaoImpl;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public List<User> queryAll() {
        return userDao.queryAll();
    }

    @Override
    public boolean deleteById(String id) {
        int deleteNum = userDao.deleteById(id);
/*        if (deleteNum == 0) {
            return false;
        } else {
            return true;
        }*/
        /*        return deleteNum == 0 ? false : true;*/
        return deleteNum != 0;
    }

    @Override
    public User queryById(String id) {
        return userDao.queryById(id);
    }

    @Override
    public boolean updateUser(User user) {
        int updateNum = userDao.updateUser(user);
        return updateNum != 0;
    }

    @Override
    public boolean saveUser(User user) {
        int addNum = userDao.saveUser(user);
        return addNum != 0;
    }


    @Override
    public boolean queryByUserName(String userName) {
        List<User> userList = userDao.queryByUserName(userName);
        //先判断集合不为空
        return userList != null && userList.size() > 0;
    }

    @Override
    public List<User> queryByQq(String qq) {
        return null;
    }

    @Override
    public List<User> pageQuery(int startCount, int pageSize) {
/*        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        return userDao.pageQuery(startCount, pageSize);

    }

    @Override
    public int queryTotalCount() {
        return userDao.queryTotalCount();
    }

}
