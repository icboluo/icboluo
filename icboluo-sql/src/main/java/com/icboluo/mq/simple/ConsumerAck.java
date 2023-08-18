package com.icboluo.mq.simple;

import com.icboluo.constant.MqConstant;
import com.icboluo.util.MqConnectionUtil;
import com.icboluo.util.IcBoLuoException;
import com.rabbitmq.client.*;
import lombok.SneakyThrows;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author icboluo
 * @since 2021-10-25 23:30
 */
public class ConsumerAck {

    @SneakyThrows
    public static void main(String[] args) {
        Connection conn = MqConnectionUtil.getConnection();
        Channel channel = conn.createChannel();
        channel.queueDeclare(MqConstant.QUEUE_NAME_SIMPLE, false, false, false, null);
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                if (1 == 1) {
                    throw new IcBoLuoException();
                }
//                信封获取交换机
                String exchange = envelope.getExchange();
                System.out.println("exchange: " + exchange);
//                信封获取交货标签（消息id，mq在channel中用来标识消息的id，可用于确认消息已接受
                long deliveryTag = envelope.getDeliveryTag();
                System.out.println("deliveryTag: " + deliveryTag);
//                body 消息体
                String msg = new String(body, StandardCharsets.UTF_8);
                System.out.println("message have already accept ,that message is :" + msg);
                /**
                 * 手动进行ack，和事物提交一样（如果不加这一句，事物一直不提交，消息会unacked状态，服务关闭，才会释放
                 * multiple：是否批量true，将一次性ack所有小于deliveryTag的消息
                 */
                channel.basicAck(deliveryTag, false);
            }
        };
        /**
         * 基本消费（监听队列
         * queue：队列名称
         * autoAck：自动回复（当消费者接收到消息后要告诉mq消息已接受（true：会自动回复；false：要通过编程实现回复，相当于开启事物
         * callback:消费方法，当消费者接收到消息要执行的方法
         */
        channel.basicConsume(MqConstant.QUEUE_NAME_SIMPLE, false, consumer);
    }
}
