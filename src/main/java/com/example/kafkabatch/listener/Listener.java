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
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class Listener {

    private final Sender sender;

    @KafkaListener(id = "batch-listener", topics = "${app.topic.batch}", containerFactory = "kafkaListenerContainerFactory")
//    public void receive( ConsumerRecords<String, Client> messages, Acknowledgment acknowledgment)
    public void receive(@Payload List<Client> messages, Acknowledgment acknowledgment,
                        @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets,
                        @Header(KafkaHeaders.ACKNOWLEDGMENT) List<Acknowledgment> acknowledgments)
    {

        log.info("SIZE MSG: " + messages.size());

        log.info("Batch");

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

        for (Client client : messages) {
            if (client.getName().equals("message 9")) {
//                sender.send(Client.newBuilder()
//                    .setName("message 11")
//                    .setUuid(client.getUuid())
//                    .build().toByteArray());
//                throw new MessagerError(client, "algum erro");
            }
        }

        acknowledgments.forEach(Acknowledgment::acknowledge);

        log.info("comitado");
    }
}



