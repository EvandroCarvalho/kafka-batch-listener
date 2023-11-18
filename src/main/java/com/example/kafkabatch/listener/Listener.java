package com.example.kafkabatch.listener;

import com.example.kafkabatch.error.MessagerError;
import com.example.kafkabatch.model.ClientModel;
import com.example.kafkabatch.model.ClientModel.Client;
import com.example.kafkabatch.sender.Sender;
import java.util.Iterator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class Listener {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @KafkaListener(id = "batch-listener", topics = "${app.topic.batch}",
            containerFactory = "kafkaListenerContainerFactory", autoStartup = "false")
    public void receive( Client messages, Acknowledgment acknowledgment)
//    public void receive(@Payload List<Client> messages, Acknowledgment acknowledgment,
//                        @Header(KafkaHeaders.RECEIVED_PARTITION_ID) Integer partitions,
//                        @Header(KafkaHeaders.OFFSET) Long offsets,
//                        @Header(KafkaHeaders.ACKNOWLEDGMENT) Acknowledgment acknowledgments)
    {

//        log.info("SIZE MSG: " + messages.size());

        log.info("RECEIVED MESSAGE from topic " + messages.getName());

//        final Iterator<ConsumerRecord<String, Client>> iterator = messages.records("batch.t").iterator();

//        while (iterator.hasNext()) {
//            final Client client = iterator.next().value();
//            log.info(client.getName());
//            log.info(client.getUuid());

//            if(client.getName().equals("message 10")) {
//                acknowledgment.nack(10);
//                throw new RuntimeException("e   rro proposital");
//            }
//        }

//        for (Client client : messages) {
//            if (client.getName().equals("message 9")) {
//                sender.send(Client.newBuilder()
//                    .setName("message 11")
//                    .setUuid(client.getUuid())
//                    .build().toByteArray());
//                throw new MessagerError(client, "algum erro");
//            }
//        }

//        acknowledgments.forEach(Acknowledgment::acknowledge);

        log.info("comitado");

        acknowledgment.acknowledge();
    }
}



