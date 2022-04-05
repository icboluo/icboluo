package com.icboluo.mq.simple;

import com.icboluo.constant.MqConstant;
import com.icboluo.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import lombok.SneakyThrows;

import java.nio.charset.StandardCharsets;

/**
 * @author icboluo
 * @since 2021-10-25 22:52
 */
public class Product {

    @SneakyThrows
    public static void main(String[] args) {
        Connection conn = ConnectionUtil.getConnection();
//        从链接中创建通道，使用通道才能完成消息的相关操作
        Channel channel = conn.createChannel();
        /**
         * 队列声明（创建队列
         * durable：是否持久化（如果持久化，mq重启队列还在
         * exclusive：是否独占连接（队列只允许在该连接中访问
         * autoDelete：自动删除（队列不再使用时是否自动删除该队列
         * arguments：参数（可以设置一个队列的扩展参数，比如：可以设置存活时间
         */
//        如果访问失败，可以在client页面 admin中 set permission 点击一下
        channel.queueDeclare(MqConstant.QUEUE_NAME_SIMPLE, false, false, false, null);
        String message = "hello rabbit mq";
        /**
         * exchange:交换机（如果不指定，将使用mq的默认交换机 设置为""
         * routingKey：路由key，交换机根据路由key来将消息转发到指定的队列，如果使用默认交换机，routingKey设置为队列的名称
         * props：消息的属性
         * body：消息内容
         */
        channel.basicPublish("", MqConstant.QUEUE_NAME_SIMPLE, null, message.getBytes(StandardCharsets.UTF_8));
        System.out.println(MqConstant.QUEUE_NAME_SIMPLE + " send message ：" + message);
        channel.close();
        conn.close();
    }
}
