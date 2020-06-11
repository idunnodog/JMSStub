package com.radiabeam.stub.app.JMS;

import com.radiabeam.stub.app.Collector;
import com.radiabeam.stub.config.ConfigHandler;
import com.tibco.tibjms.TibjmsXAQueueConnectionFactory;
import org.apache.log4j.Logger;

import javax.jms.*;

public class JmsListener {
    private ConfigHandler configHandler;
    private Logger logger;
    private String queue;
    private String tibcoHost = "185.209.29.240";
    private String tibcoPort = "8222";
    private String tibcoUsername = "admin";
    private String tibcoPassword = "";
    private int tibcoListenerThreads = 2;
    private int tibcoListenerSessions = 5;
    private QueueConnectionFactory factory;
    private QueueConnection connection;
    private Collector collector;


    public JmsListener(ConfigHandler configHandler,Logger logger,String queue,Collector collector){
        this.configHandler = configHandler;
        this.logger = logger;
        this.queue = queue;
        this.tibcoHost = configHandler.getTibcoHost();
        this.tibcoPort = configHandler.getPort();
        this.tibcoUsername = configHandler.getUsername();
        this.tibcoPassword = configHandler.getPassword();
        this.tibcoListenerThreads = configHandler.getTibcoListenerThreads();
        this.tibcoListenerSessions = configHandler.getTibcoListenerSessions();
        this.collector = collector;
        setUpConnection();
        collector.createBlockingQueue(queue);
    }

    private void setUpConnection(){
        try {
            this.factory = new TibjmsXAQueueConnectionFactory("tcp://"+tibcoHost+":"+tibcoPort);
            this.connection = factory.createQueueConnection(tibcoUsername,tibcoPassword);
            this.connection.start();
        } catch (JMSException e) {
            logger.error("LISTENER: Can not create a connection to tibco: "+"tcp://"+tibcoHost+":"+tibcoPort,e);
            e.printStackTrace();
        }
    }

    private Thread getListenerThread(int iteration){
        return new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
                    Queue receiverQueue = session.createQueue(queue);
                    MessageConsumer messageConsumer = session.createConsumer(receiverQueue);
                    messageConsumer.setMessageListener(new JmsConsumerMessageListener("x"+iteration,collector,queue));
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void startListen(){
        for (int i =0; i < tibcoListenerSessions; i++){
            getListenerThread(i).start();
        }
    }


}
