package com.icboluo.mysql;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransferDemo {
    public static void main(String[] args) {
        Connection connection = MyJdbcUtil.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            connection.setAutoCommit(false);//手动开启事物
            //转账500
            String sql1 = "update account set money=money-?where name=?";
            preparedStatement = connection.prepareStatement(sql1);
            preparedStatement.setDouble(1, 500);
            preparedStatement.setString(2, "jack");
            preparedStatement.executeUpdate();
            //收账500
            String sql2 = "update account set money=money-?where name=?";
            preparedStatement.setDouble(1, -500);
            preparedStatement.setString(2, "rose");
            preparedStatement.executeUpdate();
            //转账业务成功后，提交
            connection.commit();
            System.out.println(true);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(false);
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            MyJdbcUtil.release(null, preparedStatement, connection);
        }
    }
}
