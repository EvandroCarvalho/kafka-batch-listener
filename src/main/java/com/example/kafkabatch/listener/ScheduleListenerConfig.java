package com.example.kafkabatch.listener;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScheduleListenerConfig {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;


    @Scheduled(cron = "1 * * * * ?")
    public void start() {
        LOGGER.info("### START LISTENER");
        kafkaListenerEndpointRegistry.start();
    }

    @Scheduled(cron = "40 * * * * ?")
    public void stop() {
        LOGGER.info("### STOP LISTENER");
        kafkaListenerEndpointRegistry.stop();
    }
}
