package com.cloudcomputing.imagerecognizer.webtier.aws;

public interface SQSService {

    void pushToQueue(String messageBody, String queueName, Integer delay) throws Exception;

    boolean consumeFromQueue();

    Integer getInFlightMessagesCount();
}
