package com.radiabeam.stub.config.Model;

import java.util.List;

public class Queue {
    private String name;
    private String queuePath;
    private ListenerParam listenerParam;
    private PublisherParam publisherParam;
    private List<Correlation> correlations;

    @Override
    public String toString() {
        return "Queue{" +
                "name='" + name + '\'' +
                ", queuePath='" + queuePath + '\'' +
                ", listenerParam=" + listenerParam +
                ", publisherParam=" + publisherParam +
                ", correlations=" + correlations +
                '}';
    }

    public String getQueuePath() {
        return queuePath;
    }

    public void setQueuePath(String queuePath) {
        this.queuePath = queuePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ListenerParam getListenerParam() {
        return listenerParam;
    }

    public void setListenerParam(ListenerParam listenerParam) {
        this.listenerParam = listenerParam;
    }

    public PublisherParam getPublisherParam() {
        return publisherParam;
    }

    public void setPublisherParam(PublisherParam publisherParam) {
        this.publisherParam = publisherParam;
    }

    public List<Correlation> getCorrelations() {
        return correlations;
    }

    public void setCorrelations(List<Correlation> correlations) {
        this.correlations = correlations;
    }
}
