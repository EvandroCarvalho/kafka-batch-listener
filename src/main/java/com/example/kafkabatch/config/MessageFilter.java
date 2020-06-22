package com.example.kafkabatch.config;

import com.example.kafkabatch.model.ClientModel.Client;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;
import org.springframework.stereotype.Component;

@Component
public class MessageFilter implements RecordFilterStrategy<String, Client> {

    @Override
    public boolean filter(ConsumerRecord<String, Client> consumerRecord) {
        final Client client = consumerRecord.value();

        return client.getName().equals("message 10");
    }
}
