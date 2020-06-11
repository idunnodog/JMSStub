package com.radiabeam.stub.app.JMS;


import com.radiabeam.stub.app.Collector;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class JmsConsumerMessageListener implements MessageListener {
    private final Collector collector;
    private final String queueName;


    public JmsConsumerMessageListener(String consumerName, Collector collector,String queueName) {
        this.collector = collector;
        this.queueName = queueName;
    }

    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            collector.putMessageToBlockingQueue(queueName,textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}