package com.icboluo.mq.work;

import com.icboluo.common.MqConstant;
import com.icboluo.util.ConnectionUtil;
import com.rabbitmq.client.*;
import lombok.SneakyThrows;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author icboluo
 * @date 2021-10-25 23:55
 */
public class Receive2 {
    @SneakyThrows
    public static void main(String[] args) {
        Connection conn = ConnectionUtil.getConnection();
        Channel channel = conn.createChannel();
        channel.queueDeclare(MqConstant.QUEUE_NAME_WORK, false, false, false, null);

        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, StandardCharsets.UTF_8);
                System.out.println("message have already accept ,that message is :" + msg);
            }
        };
        channel.basicConsume(MqConstant.QUEUE_NAME_WORK, true, consumer);
    }
}
