package com.icboluo.ee.mysql;

import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
二.此时测试代码有1个问题：
数据源用接口比类扩展好：修改为 DataSource dataSource=new MyDataSource()；
可是DataSource没有backToPools方法
一个方法要增强他的功能有几个方式：
1.重写：没有connection接口的实现类
2.装饰设计模式：可以实现，但需要重写好多方法
3.动态代理：增强close方法，实现将连接放回连接池
 */
public class MyDataSourceTest {
    @Test
    public void test1() throws SQLException {
        //创建出一个数据源
        MyDataSource dataSource = new MyDataSource();
        //取出连接去操作数据库，此时返回的连接就是一个增强了close方法的连接的代理对象
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(MyJdbcUtil.UPDATE_SQL);
        preparedStatement.setString(1, "123456");
        int executeUpdate = preparedStatement.executeUpdate();
        System.out.println(executeUpdate);
        //下面2中方法吧连接放到连接池中是一样的，第一种会用动态代理增强backToPools
        MyJdbcUtil.release(null, preparedStatement, connection);
/*        MyJdbcUtil.release(null, preparedStatement, null);
        dataSource.backToPools(connection);*/
    }

    @Test
    public void test2() throws SQLException {
        DataSource c3P0DataSource = MyDataSourceFactory.getDataSource(1);
        DataSource druidDataSource = MyDataSourceFactory.getDataSource(2);
        Connection connection = druidDataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(MyJdbcUtil.UPDATE_SQL);
        preparedStatement.setString(1, "12345");
        int i = preparedStatement.executeUpdate();
        System.out.println(i);
        MyJdbcUtil.release(null, preparedStatement, connection);
    }

    /**
     * druid连接池
     *
     * @throws SQLException
     */
    @Test
    public void test3() throws SQLException {
        for (int i = 0; i < 11; i++) {
            Connection connection = MyJdbcUtil.getConnection();
            System.out.println(i + "---" + connection);
            if (i == 5) {
                connection.close();
            }
        }
    }

    /**
     * c3p0连接池
     */
    @Test
    public void tests4() throws SQLException {
        for (int i = 0; i < 11; i++) {
            Connection connection = MyJdbcUtil.getConnection2();
            System.out.println(i + "=" + connection);
            if (i == 5) {
                connection.close();//索引为5，将连接放回连接池
            }
        }
    }
}
