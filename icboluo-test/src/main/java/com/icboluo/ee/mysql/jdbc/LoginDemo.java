package com.icboluo.ee.mysql.jdbc;


import com.icboluo.ee.mysql.MyJdbcUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginDemo {
    public static void main(String[] args) throws SQLException {
        String username = "a";
        String password = "b";
        Connection connection = MyJdbcUtil.getConnection();
        Statement statement = connection.createStatement();
        String sql = "select count(*)from user where username = '" + username + "' and password = '" + password + "';";
        ResultSet resultSet = statement.executeQuery(sql);
        int count = 0;
        while (resultSet.next()) {
            count = resultSet.getInt("count(*)");
        }
        System.out.println(count > 0 ? "登录成功" : "登录失败");
        MyJdbcUtil.release(resultSet, statement, connection);
    }
}
