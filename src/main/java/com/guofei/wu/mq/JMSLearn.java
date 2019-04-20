package com.guofei.wu.mq;


import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTempQueue;

import javax.jms.*;
import javax.naming.InitialContext;

/**
 * @author Mason
 * @version v1.0
 * @since 2019/2/26
 */
public class JMSLearn {


    public static void main(String[] args) throws Exception {


    }


    public void producer() throws Exception {
        // 创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();

        // 通过连接工厂创建连接
        Connection connection = connectionFactory.createConnection();

        connection.start();

        // 通过连接创建回话
        Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);

        Destination destination = session.createQueue("queue");

        MessageProducer producer = session.createProducer(destination);

        Message message = session.createTextMessage("text message");

        producer.send(message);


        // close operate
    }

    public void consumer() throws Exception {
        // 创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();

        // 通过连接工厂创建连接
        Connection connection = connectionFactory.createConnection();

        connection.start();

        // 通过连接创建回话
        Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);

        Destination destination = session.createQueue("queue");

        QueueReceiver consumer = (QueueReceiver) session.createConsumer(destination);

        consumer.setMessageListener((message) -> {
            TextMessage textMessage = (TextMessage) message;
            try {
                System.out.println(textMessage.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        });

        // close operate
    }


    public void useContext() throws Exception {
        // 创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        InitialContext jndiContext = new InitialContext();
        ConnectionFactory cf = (ConnectionFactory) jndiContext.lookup("");
    }


}
