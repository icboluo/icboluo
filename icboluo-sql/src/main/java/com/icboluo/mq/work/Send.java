package com.icboluo.mq.work;

import com.icboluo.common.MqConstant;
import com.icboluo.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import lombok.SneakyThrows;

import java.nio.charset.StandardCharsets;

/**
 * @author icboluo
 * @date 2021-10-25 23:44
 */
public class Send {

    @SneakyThrows
    public static void main(String[] args) {
        Connection conn = ConnectionUtil.getConnection();
        Channel channel = conn.createChannel();
        channel.queueDeclare(MqConstant.QUEUE_NAME_WORK, false, false, false, null);
        for (int i = 0; i < 50; i++) {
            String msg = MqConstant.MSG.apply(i);
            channel.basicPublish("", MqConstant.QUEUE_NAME_WORK, null, msg.getBytes(StandardCharsets.UTF_8));
            System.out.println(msg);
            Thread.sleep(i * 2);
        }
        channel.close();
        conn.close();
    }
}
