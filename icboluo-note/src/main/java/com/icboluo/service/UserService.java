package com.icboluo.service;


import com.icboluo.entity.base.User;
import com.icboluo.mapper.UserMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
        User user = new User();
        user.setId(id);
        user.setPassword(password);
        int i = userMapper.updateByPrimaryKeySelective(user);
        return i == 1;
    }
}
