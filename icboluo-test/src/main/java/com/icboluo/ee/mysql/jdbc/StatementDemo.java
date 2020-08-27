package com.icboluo.ee.mysql.jdbc;

import com.icboluo.ee.mysql.MyJdbcUtil;
import org.junit.Test;

import java.sql.*;

public class StatementDemo {
    @Test
    public void executeQuryTest() throws SQLException {
        Connection connection = MyJdbcUtil.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(MyJdbcUtil.SELECT_SQL);
        MyJdbcUtil.parseResultSet(resultSet);
        MyJdbcUtil.release(resultSet, statement, connection);
    }

    @Test
    public void executeUpdateTest() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///day04_db", "root", "root");
        Statement statement = connection.createStatement();
        String sql = "insert into user values(null,'赵六','123')";
        //返回受影响的行数
        int i = statement.executeUpdate(sql);
    }

    @Test
    public void executeTest() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///day04_db", "root", "root");
        Statement statement = connection.createStatement();
        String sql = "update user set password='321'";
        //ctrl+q 显示api
        boolean result = statement.execute(sql);
        //true is a ResultSet object; false if it is an update count or there are no results
        if (result) {
        } else {
            int updateCount = statement.getUpdateCount();
            System.out.println("updateCount = " + updateCount);
        }
    }
}
