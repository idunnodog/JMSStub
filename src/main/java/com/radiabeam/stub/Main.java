package com.radiabeam.stub;

import com.radiabeam.stub.config.Model.Conf;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;



public class Main {
    static final Logger logger = Logger.getLogger(Main.class);


    public static void main(String[] args) {
        BasicConfigurator.configure();
        logger.info("We are going to start application...");
        //Add config parameters
        //ConfigHandler configHandler = new ConfigHandler("/Users/computer/IdeaProjects/JMSStub_v2/src/main/resources/config.ini",logger);

        Yaml yaml = new Yaml();



        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream in = classloader.getResourceAsStream("config.yaml");
        Conf conf = yaml.loadAs(in, Conf.class);
        //System.out.println(conf.toString());
        System.out.println(conf.getQueueList());
        for(String queue :conf.getQueueList()){
            System.out.println(conf.getQueueByName(queue));
        }













        //Add collector
        //Collector collector = new Collector(logger);

        //Add Jms Listener for 1 queue
        //JmsListener jmsListener = new JmsListener(configHandler,logger,"LT.WB.rq",collector);
        //for(int i=0;i<configHandler.getTibcoListenerThreads();i++){
        //    jmsListener.startListen();
        //}
        //System.out.println("Added jms listener");


        //Add Jms Publisher for 1 queue
        //JmsPublisher jmsPublisher = new JmsPublisher(configHandler,logger,"LT.WB.rq",collector,"LT.WB.rs");
        //for(int i=0;i<configHandler.getTibcoPublisherThreads();i++){
        //    jmsPublisher.startListen();
        //}
        //System.out.println("Added jms publisher");

        //LtWbAgreementList3C l = new LtWbAgreementList3C();
        //System.out.println(l.getResponse("1234123412341234","12345"));












        //test
        /*TestSender sender = null;
        try {
            sender = new TestSender(configHandler,logger,"LT.WB.rq");
        } catch (JMSException e) {
            e.printStackTrace();
        }
        try {
            assert sender != null;
            sender.startListen();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/


    }






}
