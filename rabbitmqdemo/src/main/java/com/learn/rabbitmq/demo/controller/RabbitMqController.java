package com.learn.rabbitmq.demo.controller;

import com.learn.rabbitmq.demo.entity.Account;
import com.learn.rabbitmq.demo.entity.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guofei.wu
 * @version v1.0
 * @date 2022/3/12 14:57
 * @since v1.0
 */
@RestController
public class RabbitMqController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/sendMsg")
    public String sendMsg(@RequestParam(value = "num", defaultValue = "10") Integer num) {

        for (int i = 0; i < num; i++) {
            if (i % 2 == 0) {
                User msg = new User();
                msg.setId(1L);
                msg.setUserName("Mason" + i);
                msg.setEmail("a@email.com");
                rabbitTemplate.convertAndSend("hello-java-exchange", "hello.java", msg);
            } else {
                Account msg = new Account();
                msg.setAccount("Mason" + i);
                msg.setPwd("a@email.com");
                rabbitTemplate.convertAndSend("hello-java-exchange", "hello.java", msg);
            }

        }
        return "ok";
    }


    @GetMapping("/sendDelayMsg")
    public String sendDelayMsg() {
        User msg = new User();
        msg.setId(1L);
        msg.setUserName("Mason");
        msg.setEmail("a@email.com");
        rabbitTemplate.convertAndSend("delay-consumer-exchange", "delay.routing.key", msg);
        return "ok";
    }
}
