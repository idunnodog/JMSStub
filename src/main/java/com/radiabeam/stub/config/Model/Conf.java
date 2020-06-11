package com.radiabeam.stub.config.Model;

import java.util.ArrayList;
import java.util.List;

public class Conf {
    private List<Connection> connections;
    private List<Queue> queues;

    @Override
    public String toString() {
        return "Conf{" +
                "connections=" + connections +
                ", queues=" + queues +
                '}';
    }

    public List<Connection> getConnections() {
        return connections;
    }

    public void setConnections(List<Connection> connections) {
        this.connections = connections;
    }

    public List<Queue> getQueues() {

        return queues;
    }

    public void setQueues(List<Queue> queues) {
        this.queues = queues;
    }

    public Queue getQueueByName(String queueName){
        for (Queue customer : queues) {
            if (customer.getName().equals(queueName)) {
                return customer;
            }
        }
        return null;
    }

    public List<String> getQueueList(){
        ArrayList<String> result = new ArrayList<String>();
        for (Queue queue : queues) {
            result.add(queue.getName());
        }
        return result;
    }

}
