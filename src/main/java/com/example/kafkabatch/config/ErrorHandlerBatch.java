package com.example.kafkabatch.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.kafka.listener.BatchErrorHandler;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ErrorHandlerBatch implements BatchErrorHandler {

    @Override
    public void handle(Exception e, ConsumerRecords<?, ?> consumerRecords) {
        log.info(consumerRecords.toString());
    }

    @Override
    public void handle(Exception thrownException, ConsumerRecords<?, ?> data, Consumer<?, ?> consumer) {
        log.info(data.toString());
    }

    @Override
    public void clearThreadState() {

    }

    @Override
    public boolean isAckAfterHandle() {
        return false;
    }

    @Override
    public void handle(Exception thrownException, ConsumerRecords<?, ?> data, Consumer<?, ?> consumer, MessageListenerContainer container) {
        //Catch deserializer error
        log.info(data.toString());
    }

    @Override
    public void handle(Exception thrownException, ConsumerRecords<?, ?> data, Consumer<?, ?> consumer, MessageListenerContainer container, Runnable invokeListener) {
        log.info("ERROR: " + data.toString());
    }
}
