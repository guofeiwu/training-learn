package com.guofei.wu.mq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author Mason
 * @version v1.0
 * @since 2019/2/20
 */
public class JMSProducer {

    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER; // 默认用户名
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD; // 默认密码
    private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL; // 默认连接地址

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
            Destination destination;
            destination = session.createQueue("FirstQueue1");
//            destination = session.createTopic("FirstQueue2");
            // 创建消息生产者
            MessageProducer producer = session.createProducer(destination);

            TextMessage message1 = session.createTextMessage("message1");
            // 发送消息
            producer.send(message1, DeliveryMode.PERSISTENT, 1, 1000 * 60 * 60 * 24);
//            producer.send(message1);


            producer.close();
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }


    }

}
