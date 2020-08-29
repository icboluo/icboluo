package com.icboluo.mysql.jdbctemplate;

import com.icboluo.dataobject.User;
import com.icboluo.mysql.MyDataSourceFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class JdbcTemplateLoginWeb {
    public static void main(String[] args) {
        String username = "lisi";
        String password = "123456";

        boolean result = loginService(username, password);
    }

    public static boolean loginService(String username, String password) {
        boolean result = loginDao(username, password);
        return result;
    }

    public static boolean loginDao(String username, String password) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(MyDataSourceFactory.getDataSource(2));
        String sql = "select*from user where username=? and password=?";
        List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), username, password);
        return users.size() > 0;
    }
}
