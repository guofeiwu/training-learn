package com.learn.rabbitmq.demo.service;

import com.learn.rabbitmq.demo.entity.User;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author guofei.wu
 * @version v1.0
 * @date 2022/3/12 15:00
 * @since v1.0
 */
//@Service
public class RabbitMqConsumer {


    @RabbitListener(queues = {"hello-java-queue"})
    public void received(Message message, Channel channel) {
        // System.out.println(message);

        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            if (deliveryTag % 2 == 0) {
                channel.basicAck(deliveryTag, false);
                System.out.println("消息被消费：" + deliveryTag);
            } else {
                channel.basicNack(deliveryTag, false, true);
                System.out.println("消息没有被消费：" + deliveryTag);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //@RabbitListener(queues = {"hello-java-queue"})
    public void received2(Message message, User user, Channel channel) {
        System.out.println("message:" + message);
        System.out.println("user:" + user);
        System.out.println("channel:" + channel);
    }

}
