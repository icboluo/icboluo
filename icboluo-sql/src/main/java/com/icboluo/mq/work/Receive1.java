package com.icboluo.mq.work;

import com.icboluo.constant.MqConstant;
import com.icboluo.util.ConnectionUtil;
import com.rabbitmq.client.*;
import lombok.SneakyThrows;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * @author icboluo
 * @since 2021-10-25 23:55
 */
public class Receive1 {
    @SneakyThrows
    public static void main(String[] args) {
        Connection conn = ConnectionUtil.getConnection();
        Channel channel = conn.createChannel();
        channel.queueDeclare(MqConstant.QUEUE_NAME_WORK, false, false, false, null);

        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, StandardCharsets.UTF_8);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("message have already accept ,that message is :" + msg);
            }
        };
        channel.basicConsume(MqConstant.QUEUE_NAME_WORK, true, consumer);
    }
}
