package com.learn.rabbitmq.demo.service;

import com.learn.rabbitmq.demo.entity.Account;
import com.learn.rabbitmq.demo.entity.User;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author guofei.wu
 * @version v1.0
 * @date 2022/3/12 15:00
 * @since v1.0
 */
//@Service
//@RabbitListener(queues = {"hello-java-queue"})
public class RabbitMqConsumer2 {


    //@RabbitListener(queues = {"hello-java-queue"})
    @RabbitHandler
    public void received(Message message, Account account) {
        System.out.println("account:"+account);
    }

    @RabbitHandler
    public void received2(Message message, User user, Channel channel) {
        System.out.println("user:" + user);
    }

}
