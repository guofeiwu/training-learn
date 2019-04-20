package com.guofei.wu.mq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author Mason
 * @version v1.0
 * @since 2019/2/20
 */
public class JMSConsumer {


    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER; // 默认用户名
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD; // 默认密码
    private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL; // 默认连接地址
    private static final int SENDNUM = 10; // 定义发送的消息数量

    public static void main(String[] args) {
        // 实例化连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKEURL);
        try {
            // 通过连接工厂获取连接
            Connection connection = connectionFactory.createConnection();
            // 启动连接
            connection.start();

            // 获取Session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // 创建消息队列，名为FirstQueue1
            Destination destination = null;
            destination = session.createQueue("FirstQueue1");
//            destination = session.createTopic("FirstQueue2");
            // 创建消息消费者
            MessageConsumer consumer = session.createConsumer(destination);

            TextMessage receive = (TextMessage) consumer.receive();
            System.out.println(receive.getText());
//            consumer.setMessageListener(message -> {
//                TextMessage receive = (TextMessage) message;
//                try {
//                    System.out.println("receive message: " + receive.getText());
//                } catch (JMSException e) {
//                    e.printStackTrace();
//                }
//            });
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
