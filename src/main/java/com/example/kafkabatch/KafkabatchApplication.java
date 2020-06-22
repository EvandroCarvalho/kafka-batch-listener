package com.example.kafkabatch;

import com.example.kafkabatch.model.ClientModel;
import com.example.kafkabatch.model.ClientModel.Client;
import com.example.kafkabatch.sender.Sender;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

@Slf4j
@SpringBootApplication
public class KafkabatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(KafkabatchApplication.class, args);
	}

	@Autowired
	private Sender sender;

	@Value("${app.topic.batch}")
	private String topic;

	@Override
	public void run(String... args) throws Exception {
		for (int i = 1; i < 13; i++) {
			final byte[] byteArray = Client.newBuilder()
				.setUuid(UUID.randomUUID().toString())
				.setName("message " + i)
				.build().toByteArray();
			sender.send(byteArray);

			log.info("MENSAGEM ENVIADA " + i);
//
//			final Message<byte[]> message = MessageBuilder.withPayload(byteArray)
//				.setHeader(KafkaHeaders.MESSAGE_KEY, "111")
//				.setHeader(KafkaHeaders.TOPIC, topic)
//				.build();
//
////			sender.send(message);
//			kafkaTemplate.send(message);
		}
	}
}
