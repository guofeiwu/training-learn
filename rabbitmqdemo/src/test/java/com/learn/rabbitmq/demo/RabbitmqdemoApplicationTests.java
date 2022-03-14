package com.learn.rabbitmq.demo;

import com.learn.rabbitmq.demo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RabbitmqdemoApplicationTests {

    @Autowired
    private AmqpAdmin amqpAdmin;


    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    void sendMessage() {
        String msg = "Hello World!";
        rabbitTemplate.convertAndSend("hello-java-exchange", "hello.java", msg);
        System.out.println("send msg success");
    }

    @Test
    void sendMessageJson() {
        User msg = new User();
        msg.setId(1L);
        msg.setUserName("Mason");
        msg.setEmail("a@email.com");
        rabbitTemplate.convertAndSend("hello-java-exchange", "hello.java", msg);
        System.out.println("send json msg success");
    }


    @Test
    void createExchange() {
        //	public DirectExchange(String name, boolean durable, boolean autoDelete, Map<String, Object> arguments) {
        Exchange exchange = new DirectExchange("hello-java-exchange", true, false, null);
        amqpAdmin.declareExchange(exchange);
        System.out.println("create exchange success");
    }

    @Test
    void createQueue() {
        //String name, boolean durable, boolean exclusive, boolean autoDelete,
        //			@Nullable Map<String, Object> arguments
        Queue queue = new Queue("hello-java-queue", true, false, false, null);
        amqpAdmin.declareQueue(queue);
        System.out.println("create queue success");

    }


    @Test
    void createBinding() {
        // String destination, DestinationType destinationType, String exchange, String routingKey,
        //			@Nullable Map<String, Object> arguments
        Binding binding = new Binding("hello-java-queue",
                Binding.DestinationType.QUEUE,
                "hello-java-exchange",
                "hello.java",
                null);
        amqpAdmin.declareBinding(binding);
        System.out.println("create binding success");

    }

}









