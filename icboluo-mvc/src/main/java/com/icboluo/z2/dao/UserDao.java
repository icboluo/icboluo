package com.icboluo.z2.dao;


import com.icboluo.z2.bean.User;

import java.util.List;

public interface UserDao {
    List<User> queryAll();

    int deleteById(String id);

    User queryById(String id);

    int updateUser(User user);

    int saveUser(User user);

    List<User> pageQuery(int startCount, int pageSize);

    int queryTotalCount();

    List<User> queryByUserName(String userName);
}
