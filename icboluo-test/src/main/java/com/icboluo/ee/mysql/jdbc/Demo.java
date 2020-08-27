package com.icboluo.ee.mysql.jdbc;

import com.icboluo.ee.mysql.MyJdbcUtil;

import java.sql.*;

/**
 * JDBC有关的类：
 * DriverManger:数据库驱动类,不同的数据库有不同的数据库驱动，用来注册驱动。
 * Connection:数据库连接，建立一个操作数据库的连接。
 * Statement:数据库操作，向数据库发送sql语句
 * ResultSet:结果集，Statement执行完sql返回的结果,封装在了ResultSet中
 * jdbc步骤：
 * 1.注册数据库驱动：DriverManager.registerDriver(new Driver());
 * 2.获取数据库连接：Connection conn = DriverManager.getConnection(url, user, password);
 * 3.创建发送sql的statement对象：Statement st = conn.createStatement();
 * 4.执行sql语句，并且获取返回结果;ResultSet rs = st.executeQuery(sql);
 * 5.遍历结果集;int id = rs.getInt("id");String username = rs.getString("username");
 * 说明：获取具体的结果，使用ResultSet 类的对象调用  get数据类型(列名) 来获取具体的列值。
 * 6）释放资源;
 *
 * @author icboluo
 * @date 2020-08-13 13:29
 */
class Demo {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        注册数据库驱动
        DriverManager.registerDriver(new MyDriver());
//        获取数据库连接
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/note", "root", "root");
//        创建发送sql的statement对象
        Statement st = conn.createStatement();
//        执行sql语句，并且获取返回结果
        ResultSet result = st.executeQuery(MyJdbcUtil.SELECT_SQL);
        MyJdbcUtil.parseResultSet(result);
        MyJdbcUtil.release(result, st, conn);

        //注册驱动时有2个缺陷：要注册两次（用driver匿名实现类去查看），如果改变数据库类型时，需要修改代码重写导包
        Class.forName("com.mysql.jdbc.Driver");
        //jdbc、mysql（只有mysql是这个协议，别的数据库名和协议都不相同）:协议,需要连接的数据库ip，数据库端口号，数据库名......
        Connection connection1 = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/user", "root", "root");
        //本地，默认端口号可以省略
        Connection connection2 = DriverManager.getConnection("jdbc:mysql:///user", "root", "root");
    }
}
