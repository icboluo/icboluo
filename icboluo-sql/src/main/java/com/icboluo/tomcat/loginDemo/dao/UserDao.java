package com.icboluo.tomcat.loginDemo.dao;

import com.icboluo.dataobject.User;
import com.icboluo.tomcat.loginDemo.utils.DruidUtil;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDao {
    // 初始化JdbcTemplate模板
   private JdbcTemplate jdbcTemplate = new JdbcTemplate(DruidUtil.getDateSourse());

    // 根据用户名和密码查询数据
    public User queryByUsernameAndPassword(User user) {
        String sql = "select*from t_user where username=?and password=?;";
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), user.getUsername(), user.getPassword());
        } catch (DataAccessException e) {
            return null;
        }
    }
}
