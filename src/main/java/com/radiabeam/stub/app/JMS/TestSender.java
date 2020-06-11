package com.radiabeam.stub.app.JMS;

import com.radiabeam.stub.config.ConfigHandler;
import com.tibco.tibjms.TibjmsXAQueueConnectionFactory;
import org.apache.log4j.Logger;

import javax.jms.*;

public class TestSender {
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







    public TestSender(ConfigHandler configHandler,Logger logger,String queue) throws JMSException {
        this.configHandler = configHandler;
        this.logger = logger;
        this.queue = queue;
        this.tibcoHost = configHandler.getTibcoHost();
        this.tibcoPort = configHandler.getPort();
        this.tibcoUsername = configHandler.getUsername();
        this.tibcoPassword = configHandler.getPassword();
        this.tibcoPublisherThreads = configHandler.getTibcoPublisherThreads();
        this.tibcoPublisherSessions = configHandler.getTibcoPublisherSessions();
        this.factory = new TibjmsXAQueueConnectionFactory("tcp://"+tibcoHost+":"+tibcoPort);
        this.connection = factory.createQueueConnection(tibcoUsername,tibcoPassword);

        this.connection.start();

    }
    private Thread getTestThread(int iteration){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
                    Queue destination2 = session.createQueue(queue);
                    MessageProducer msgProducer = session.createProducer(destination2);

                    while(true){
                        try {
                            TextMessage msg2;
                            msg2 = session.createTextMessage();
                            msg2.setText("Haha");
                            msg2.setStringProperty("Fuck", "Shit");
                            msgProducer.send(msg2);
                            //Thread.sleep();
                            //System.out.println("x");
                        } catch (JMSException e) {
                            e.printStackTrace();
                        }

                    }
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        return thread;
    }

    public void startListen() throws InterruptedException {
        for (int i =0; i < 100; i++){
            getTestThread(i).start();
        }
    }


}
