package com.icboluo.a_sql;

import org.junit.jupiter.api.Test;

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
 * @since 2022-05-28 23:41
 */
public class JdbcTest {

    @Test
    public void jdbc() throws SQLException, ClassNotFoundException {
        // 注册数据库驱动
        DriverManager.registerDriver(new z_MyDriver());
        //        获取数据库连接
        Connection conn = DriverManager.getConnection("jdbc:sqlite::../../../document/sql/document.db", "", "");
        // 创建发送sql的statement对象
        Statement st = conn.createStatement();
        // 执行sql语句，并且获取返回结果
        ResultSet result = st.executeQuery("select * from student limit 10");
        while (result.next()) {
            int id = result.getInt("id");
            String name = result.getString("name");
            String pwd = result.getString("code");
            System.out.println(id + "===" + name + "===" + pwd);
        }
        z_MyJdbcUtil.release(result, st, conn);

        // 注册驱动时有2个缺陷：要注册两次（用driver匿名实现类去查看），如果改变数据库类型时，需要修改代码重写导包
        // jdbc、mysql（只有mysql是这个协议，别的数据库名和协议都不相同）:协议,需要连接的数据库ip，数据库端口号，数据库名......
        Connection connection = DriverManager.getConnection("jdbc:sqlite::../../../document/sql/document.db", "", "");
        // 本地，默认端口号可以省略
        // Connection connection = DriverManager.getConnection("jdbc:mysql:///user", "root", "root");
    }

    @Test
    public void login() throws SQLException {
        String username = "a";
        String password = "b";
        Connection connection = z_MyJdbcUtil.getConnection();
        Statement statement = connection.createStatement();
        String sql = "select count(*) from user where username = '" + username + "' and password = '" + password + "';";
        ResultSet resultSet = statement.executeQuery(sql);
        int count = 0;
        while (resultSet.next()) {
            count = resultSet.getInt("count(*)");
        }
        System.out.println(count > 0 ? "登录成功" : "登录失败");
        z_MyJdbcUtil.release(resultSet, statement, connection);
    }

    @Test
    public void executeQueryTest() throws SQLException {
        Connection connection = z_MyJdbcUtil.getConnection();
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(z_MyJdbcUtil.SELECT_SQL);
        while (result.next()) {
            int id = result.getInt("id");
            String name = result.getString("name");
            String pwd = result.getString("password");
            System.out.println(id + "===" + name + "===" + pwd);
        }
        z_MyJdbcUtil.release(result, statement, connection);
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
        // ctrl+q 显示api
        boolean result = statement.execute(sql);
        // true is a ResultSet object; false if it is an update count or there are no results
        if (result) {
        } else {
            int updateCount = statement.getUpdateCount();
            System.out.println("updateCount = " + updateCount);
        }
    }
}
