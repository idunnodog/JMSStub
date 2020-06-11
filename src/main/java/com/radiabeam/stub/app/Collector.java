package com.radiabeam.stub.app;

import org.apache.log4j.Logger;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class Collector {
    private static final ConcurrentHashMap<String, BlockingQueue<String>> mapQueue = new ConcurrentHashMap<>();
    private final Logger logger;

    //init
    public Collector(Logger logger){
        this.logger = logger;
    }



    //Create blocking queue
    public void createBlockingQueue(String queueName){
        mapQueue.put(queueName, new ArrayBlockingQueue<String>(1024));
    }

    //Put message to blocking queue
    public void putMessageToBlockingQueue(String queueName,String msg){
        try {
            mapQueue.get(queueName).put(msg);
        } catch (InterruptedException e) {
            logger.error("COLLECTOR: Can't put message:\r\n"+msg+"\r\n to queue: "+ queueName );
            e.printStackTrace();
        }
    }

    //Get message from blocking queue
    public String getMessageFromBlockingQueue(String queueName){
        try {
            return mapQueue.get(queueName).take();
        } catch (InterruptedException e) {
            logger.error("COLLECTOR: Can't take message from queue: "+queueName);
            e.printStackTrace();
            return null;
        }
    }


}
