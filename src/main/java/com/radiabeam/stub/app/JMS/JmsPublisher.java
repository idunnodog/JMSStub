package com.radiabeam.stub.app.JMS;

import com.radiabeam.stub.app.Collector;
import com.radiabeam.stub.config.ConfigHandler;
import com.tibco.tibjms.TibjmsXAQueueConnectionFactory;
import org.apache.log4j.Logger;

import javax.jms.*;

public class JmsPublisher {
    private ConfigHandler configHandler;
    private Logger logger;
    private String queue;
    private String tibcoHost = "185.209.29.240";
    private String tibcoPort = "8222";
    private String tibcoUsername = "admin";
    private String tibcoPassword = "";
    private int tibcoPublisherThreads = 2;
    private int tibcoPublisherSessions = 5;
    private QueueConnectionFactory factory;
    private QueueConnection connection;
    private MessageProducer msgProducer = null;
    private Destination destination = null;
    private Collector collector;
    private String queueNameToSend;

    public JmsPublisher(ConfigHandler configHandler, Logger logger, String queue,Collector collector,String queueNameToSend){
        this.configHandler = configHandler;
        this.logger = logger;
        this.queue = queue;
        this.tibcoHost = configHandler.getTibcoHost();
        this.tibcoPort = configHandler.getPort();
        this.tibcoUsername = configHandler.getUsername();
        this.tibcoPassword = configHandler.getPassword();
        this.tibcoPublisherThreads = configHandler.getTibcoPublisherThreads();
        this.tibcoPublisherSessions = configHandler.getTibcoPublisherSessions();
        this.queueNameToSend = queueNameToSend;
        this.collector = collector;
        setUpConnection();
    }



    private void setUpConnection(){
        try {
            this.factory = new TibjmsXAQueueConnectionFactory("tcp://"+tibcoHost+":"+tibcoPort);
            this.connection = factory.createQueueConnection(tibcoUsername,tibcoPassword);
        } catch (JMSException e) {
            logger.error("PUBLISHER: Can not create a connection to tibco: "+"tcp://"+tibcoHost+":"+tibcoPort,e);
            e.printStackTrace();
        }
    }

    private Thread getPublisherThread(int iteration){
        Thread thread = new Thread(() -> {
            try {
                TextMessage msg;
                QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
                Queue destination = session.createQueue(queueNameToSend);
                MessageProducer msgProducer = session.createProducer(destination);
                String curMsg;

                while(true){
                    curMsg = collector.getMessageFromBlockingQueue(queue);
                    if(curMsg!=null){
                        msg = session.createTextMessage();
                        msg.setStringProperty("Fuck", "Shit");
                        msg.setText(curMsg);
                        msgProducer.send(msg);
                    }
                    //just sleep not to util CPU
                    //try {
                    //    Thread.sleep(5);
                    //} catch (InterruptedException e) {
                    //    e.printStackTrace();
                    //}
                }

            } catch (JMSException e) {
                e.printStackTrace();
            }
        });
        return thread;
    }

    public void startListen(){
        for (int i =0; i < tibcoPublisherSessions; i++){
            getPublisherThread(i).start();
        }
    }


}
