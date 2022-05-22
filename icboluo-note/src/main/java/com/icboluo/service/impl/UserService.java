package com.icboluo.service.impl;


import com.icboluo.mapper.UserMapper;
import com.icboluo.entity.base.UserDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author icboluo
 */
@Service
@Slf4j
public class UserService {
    @Resource
    private UserMapper userMapper;

    public boolean validate(String name, String password) {
        Integer i = userMapper.selectCountByNameAndPassword(name, password);
        return i == 1;
    }

    public boolean updatePassword(Integer id, String password) {
        UserDO userDO = new UserDO();
        userDO.setId(id);
        userDO.setPassword(password);
        int i = userMapper.updateByPrimaryKeySelective(userDO);
        return i == 1;
    }
}
