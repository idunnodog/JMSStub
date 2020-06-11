package com.radiabeam.stub.app.Rabbit;

import com.radiabeam.stub.config.ConfigHandler;

public class RabbitHandler {
    private ConfigHandler configHandler;
    private String rabbitHost = "185.209.29.240";
    private String rabbitPort = "5672";
    private String rabbitUsername = "admin";
    private String rabbitPassword = "admin";

    public RabbitHandler(ConfigHandler configHandler){
        this.configHandler = configHandler;
        this.rabbitHost = configHandler.getRabbitHost();
        this.rabbitPort = configHandler.getRabbitPort();
        this.rabbitUsername = configHandler.getRabbitUsername();
        this.rabbitPassword = configHandler.getRabbitPassword();
    }











}
