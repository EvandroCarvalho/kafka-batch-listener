package com.example.kafkabatch.listener;

import com.example.kafkabatch.model.ClientModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
public class OtherListener {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @KafkaListener(id = "other-listener", topics = "${app.topic.other}",
            containerFactory = "kafkaListenerContainerFactory", autoStartup = "false")
    public void receive(ClientModel.Client messages, Acknowledgment acknowledgment)
//    public void receive(@Payload List<Client> messages, Acknowledgment acknowledgment,
//                        @Header(KafkaHeaders.RECEIVED_PARTITION_ID) Integer partitions,
//                        @Header(KafkaHeaders.OFFSET) Long offsets,
//                        @Header(KafkaHeaders.ACKNOWLEDGMENT) Acknowledgment acknowledgments)
    {
        LOGGER.info("Receive message from other topic " + messages.getName());

        acknowledgment.acknowledge();


    }
}
