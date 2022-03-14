package com.learn.rabbitmq.demo.delay;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author guofei.wu
 * @version v1.0
 * @date 2022/3/12 17:28
 * @since v1.0
 */
@Configuration
public class MqConfig {

    /**
     * 交换机
     *
     * @return
     */
    @Bean
    public Exchange exchange() {
        //TopicExchange(String name, boolean durable, boolean autoDelete, Map<String, Object> arguments
        //Exchange exchange = new TopicExchange("delay-consumer-exchange", true, false, null);
        Exchange exchange = new DirectExchange("delay-consumer-exchange", true, false, null);

        System.out.println("创建delay-consumer-exchange成功");
        return exchange;
    }

    /**
     * 延迟队列
     *
     * @return
     */
    @Bean
    public Queue delayQueue() {

        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-message-ttl", 60000);
        arguments.put("x-dead-letter-exchange", "delay-consumer-exchange");
        // 信死了用这个路由键转发出去
        arguments.put("x-dead-letter-routing-key", "consumer.routing.key");

        Queue queue = new Queue("delay-queue", true, false, false, arguments);
        System.out.println("创建delay-queue成功");

        return queue;
    }

    /**
     * 消费队列
     *
     * @return
     */
    @Bean
    public Queue consumerQueue() {
        Queue queue = new Queue("consumer-queue", true, false, false, null);
        System.out.println("consumer-queue成功");
        return queue;
    }


    /**
     * 交换机和延迟队列绑定
     *
     * @return
     */
    @Bean
    public Binding delayQueueBinding() {

        // delay.routing.key 这个路由键发送消息到交换机

        //String destination, DestinationType destinationType, String exchange, String routingKey,
        //			@Nullable Map<String, Object> arguments
        Binding binding = new Binding("delay-queue",
                Binding.DestinationType.QUEUE,
                "delay-consumer-exchange",
                "delay.routing.key",
                null);
        System.out.println("delayQueueBinding成功");

        return binding;
    }

    /**
     * 交换机和消费队列绑定
     *
     * @return
     */
    @Bean
    public Binding consumerQueueBinding() {
        // consumer.routing.key 这个路由键发送消息到交换机

        //String destination, DestinationType destinationType, String exchange, String routingKey,
        //			@Nullable Map<String, Object> arguments
        Binding binding = new Binding("consumer-queue",
                Binding.DestinationType.QUEUE,
                "delay-consumer-exchange",
                "consumer.routing.key",
                null);
        System.out.println("consumerQueueBinding成功");
        return binding;
    }
}
