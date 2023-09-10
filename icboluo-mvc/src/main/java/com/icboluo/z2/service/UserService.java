package com.icboluo.z2.service;


import com.icboluo.z2.bean.User;

import java.util.List;

public interface UserService {
    List<User> queryAll();

    boolean deleteById(String id);

    User queryById(String id);

    boolean updateUser(User user);

    boolean saveUser(User user);

    List<User> pageQuery(int startCount, int pageSize);

    int queryTotalCount();

    List<User> queryByQq(String qq);

    boolean queryByUserName(String userName);
}
