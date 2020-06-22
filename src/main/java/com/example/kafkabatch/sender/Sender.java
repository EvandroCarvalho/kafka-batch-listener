package com.example.kafkabatch.sender;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class Sender {

    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    @Value("${app.topic.batch}")
    private String topic;

    public void send(byte[] data){
        log.info("sending message='{}' to topic='{}'", data, topic);
        kafkaTemplate.send(topic, data);
    }
}
