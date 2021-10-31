package com.icboluo.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.SneakyThrows;

/**
 * @author icboluo
 * @date 2021-10-25 22:48
 */
public class ConnectionUtil {

    @SneakyThrows
    public static Connection getConnection() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
//        页面默认端口：15672 服务端口：5672
        factory.setPort(5672);
//       设置账号信息，用户名、密码、vhost（设置虚拟机，一个mq服务可以设置多个虚拟机，每个虚拟机相当于一个独立的mq
        factory.setVirtualHost("/");
        factory.setUsername("admin");
        factory.setPassword("admin");
        return factory.newConnection();
    }
}
