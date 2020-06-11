package com.radiabeam.stub.config;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.ini4j.*;

public class ConfigHandler {
    private File file  = new File("default.ini");
    private Wini ini;
    private Logger logger;

    //list of config parameters
    //[Tibco]
    private String tibcoHost = "185.209.29.240";
    private String tibcoPort = "8222";
    private String tibcoUsername = "admin";
    private String tibcoPassword = "";
    private int tibcoListenerThreads = 2;
    private int tibcoPublisherThreads = 2;
    private int tibcoListenerSessions = 5;
    private int tibcoPublisherSessions = 5;


    //[Rabbit]
    private String rabbitHost = "185.209.29.240";
    private String rabbitPort = "5672";
    private String rabbitUsername = "admin";
    private String rabbitPassword = "admin";


    public ConfigHandler(String filepath, Logger logger){

        try {
            this.logger = logger;
            this.file = new File(filepath);
            this.ini = new Wini(this.file);
            readConfigFile(filepath);
        } catch (IOException e) {
            logger.error("File cannot be read: "+filepath,e);
            e.printStackTrace();
        }

    }


    private void readConfigFile(String filename){
        this.tibcoHost = ini.get("Tibco", "com.radiabeam.stub.tibco.host", String.class);
        this.tibcoPort = ini.get("Tibco", "com.radiabeam.stub.tibco.port", String.class);
        this.tibcoUsername = ini.get("Tibco", "com.radiabeam.stub.tibco.username", String.class);
        this.tibcoPassword = ini.get("Tibco", "com.radiabeam.stub.tibco.password", String.class);
        this.tibcoListenerThreads = ini.get("Tibco", "com.radiabeam.stub.tibco.listener.threads", int.class);
        this.tibcoPublisherThreads = ini.get("Tibco", "com.radiabeam.stub.tibco.publisher.threads", int.class);
        this.tibcoListenerSessions = ini.get("Tibco", "com.radiabeam.stub.tibco.listener.sessions", int.class);
        this.tibcoPublisherSessions = ini.get("Tibco", "com.radiabeam.stub.tibco.publisher.sessions", int.class);
        this.rabbitHost = ini.get("Rabbit", "com.radiabeam.stub.rabbit.host", String.class);
        this.rabbitPort = ini.get("Rabbit", "com.radiabeam.stub.rabbit.port", String.class);
        this.rabbitUsername = ini.get("Rabbit", "com.radiabeam.stub.rabbit.username", String.class);
        this.rabbitPassword = ini.get("Rabbit", "com.radiabeam.stub.rabbit.password", String.class);




        logger.info("The app is starting with config: "+filename+" and following parameters:"+"\r\n"+"com.radiabeam.stub.tibco.host="+this.tibcoHost+"\r\n"+
                "com.radiabeam.stub.tibco.port="+this.tibcoPort+"\r\n"+
                "com.radiabeam.stub.tibco.username="+this.tibcoUsername+"\r\n"+
                "com.radiabeam.stub.tibco.password="+this.tibcoPassword+"\r\n"+
                "com.radiabeam.stub.tibco.listener.threads="+this.tibcoListenerThreads+"\r\n"+
                "com.radiabeam.stub.tibco.publisher.threads="+this.tibcoPublisherThreads+"\r\n"+
                "com.radiabeam.stub.tibco.listener.sessions="+this.tibcoListenerSessions+"\r\n"+
                "com.radiabeam.stub.tibco.publisher.sessions="+this.tibcoPublisherSessions+"\r\n"+
                "com.radiabeam.stub.rabbit.host="+this.rabbitHost+"\r\n"+
                "com.radiabeam.stub.rabbit.port="+this.rabbitPort+"\r\n"+
                "com.radiabeam.stub.rabbit.username="+this.rabbitUsername+"\r\n"+
                "com.radiabeam.stub.rabbit.password="+this.rabbitPassword);
    }

    public String getTibcoHost() {
        return this.tibcoHost;
    }

    public String getPort() {
        return this.tibcoPort;
    }

    public String getUsername() {
        return this.tibcoUsername;
    }

    public String getPassword() {
        return this.tibcoPassword;
    }

    public int getTibcoListenerThreads() {
        return this.tibcoListenerThreads;
    }

    public int getTibcoPublisherThreads() {
        return this.tibcoPublisherThreads;
    }

    public int getTibcoListenerSessions() {
        return tibcoListenerSessions;
    }

    public int getTibcoPublisherSessions() {
        return tibcoPublisherSessions;
    }

    public String getRabbitHost() {
        return rabbitHost;
    }

    public String getRabbitPort() {
        return rabbitPort;
    }

    public String getRabbitUsername() {
        return rabbitUsername;
    }

    public String getRabbitPassword() {
        return rabbitPassword;
    }

}
