package com.icboluo.mysql;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.sql.*;
import java.util.LinkedList;
import java.util.logging.Logger;

/**
 * 一.java database connection 每次访问数据库都需要创建一个新的连接，访问完毕需要销毁
 * 该过程消耗资源远大于执行sql消耗的资源，解决办法：将connection回收利用
 * 创建多个connection形成数据库连接池，使用时取出来，不用放回去
 * 创建数据库连接池:
 * 1.因为是一个规范：创建数据源必须实现DataSource接口
 * 2.关注DataSource接口的getConnection方法，从容器中获取连接（左边stucture）
 * 3.创建出一个存放连接的容器,并在构造方法中创建一些连接放入容器
 * 4.自定义一个将连接放回容器的方法
 *
 * @author icboluo
 * @date 2020-08-13 15:06
 */
public class MyDataSource implements DataSource {
    private final LinkedList<Connection> pools = new LinkedList<>();

    /**
     * 创建数据库连接池
     */
    public MyDataSource() {
        for (int i = 0; i < 10; i++) {
            Connection connection = MyJdbcUtil.getConnection();
            pools.addFirst(connection);
        }
        System.out.println("数据库连接池创建了，当前有" + pools.size() + "个连接");
    }

    @Override
    public Connection getConnection() throws SQLException {
        //从容器中取出连接，给外部使用
        Connection connection = pools.removeLast();
        System.out.println("从数据库连接池中取出一个连接，当前有" + pools.size() + "个连接");

        InvocationHandler invocationHandler = (proxy, method, args) -> {
            //如果是close方法，就不关闭资源，将connection放回容器
            if ("close".equals(method.getName())) {
                backToPools(connection);
                return null;
            }
//            如果不是执行原本功能
            return method.invoke(connection, args);
        };
        //获得类加载器
        ClassLoader classLoader = connection.getClass().getClassLoader();
        //Class<?>[] interfaces=connection.getClass().getInterfaces();
        Class<?>[] interfaces = {Connection.class};
        return (Connection) Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
    }

    /**
     * 放回连接
     *
     * @param connection 当前连接
     */
    public void backToPools(Connection connection) {
        pools.addFirst(connection);
        System.out.println("往数据库连接池中放回一个连接，当前有" + pools.size() + "个连接");
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

    @Override
    public ShardingKeyBuilder createShardingKeyBuilder() throws SQLException {
        return null;
    }

    @Override
    public ConnectionBuilder createConnectionBuilder() throws SQLException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }
}
