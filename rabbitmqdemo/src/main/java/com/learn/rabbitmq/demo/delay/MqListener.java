package com.learn.rabbitmq.demo.delay;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.learn.rabbitmq.demo.entity.User;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author guofei.wu
 * @version v1.0
 * @date 2022/3/12 18:52
 * @since v1.0
 */
@RabbitListener(queues = {"consumer-queue"})
@Service
@Slf4j
public class MqListener {

    @RabbitHandler
    public void consumer(Message message, User user, Channel channel) throws IOException {
        System.out.println("开始消费消息....");
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        log.info("deliverTag:{},user:{}", deliveryTag, user);
        System.out.println("成功消费消息....");
        try {
            channel.basicAck(deliveryTag, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
