package com.example.kafkabatch.config;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public class ErrorHandler implements org.springframework.kafka.listener.ErrorHandler {
    @Override
    public void handle(Exception e, ConsumerRecord<?, ?> consumerRecord) {

    }

    @Override
    public void handle(Exception thrownException, ConsumerRecord<?, ?> data, Consumer<?, ?> consumer) {

    }

    @Override
    public void clearThreadState() {

    }

    @Override
    public boolean isAckAfterHandle() {
        return false;
    }
}
