package com.cloudcomputing.imagerecognizer.webtier.aws.impl;

import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.cloudcomputing.imagerecognizer.webtier.aws.AwsConfig;
import com.cloudcomputing.imagerecognizer.webtier.aws.SQSService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SQSServiceImpl implements SQSService {

    @Autowired
    private AwsConfig awsConfig;

    @Override
    public void pushToQueue(String messageBody, String queueName, Integer delay) throws Exception {
        log.debug("pushing the message to queue ");
        try {
            String queueUrl = awsConfig.getAmazonSQSClient().getQueueUrl(queueName).getQueueUrl();
            SendMessageRequest sendMessageRequest = new SendMessageRequest().withQueueUrl(queueUrl)
                    .withMessageBody(messageBody)
                    .withDelaySeconds(delay);
            awsConfig.getAmazonSQSClient().sendMessage(sendMessageRequest);
            log.info("successfully pushed the message: {} to queue: {}", messageBody, queueName);

        } catch (Exception e) {
            log.error("Unexpected error while pushing message: {} to queue: {} : ", messageBody, queueName);
            e.printStackTrace();
            throw e;
        }

    }

    @Override
    public boolean consumeFromQueue() {
        return false;
    }

    @Override
    public Integer getInFlightMessagesCount() {
        return null;
    }
}
