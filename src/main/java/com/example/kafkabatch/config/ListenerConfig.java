package com.example.kafkabatch.config;

import com.example.kafkabatch.deserializer.ClientDeserializer;
import com.example.kafkabatch.model.ClientModel.Client;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties.AckMode;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.kafka.support.LogIfLevelEnabled.Level;
import org.springframework.util.backoff.FixedBackOff;

@EnableKafka
@Configuration
public class ListenerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String boostrapServer;

    @Bean
    public Map<String, Object> consumerConfig() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, boostrapServer);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ClientDeserializer.class);
//        props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, ErrorDeserializer.class);
        props.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, 20000);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "batch");
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "10");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        return props;
    }

    @Bean
    public ConsumerFactory<String, Client> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfig());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Client> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Client> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setBatchListener(true);
//        factory.setReplyHeadersConfigurer((k,v) -> k.equals("replay"));
        factory.setBatchErrorHandler(new ErrorHandlerBatch());
//        factory.setErrorHandler(new ErrorHandler()); only when not a batch listener
//        factory.getContainerProperties().setPollTimeout(500L);
        factory.setAckDiscarded(true);
//        factory.setErrorHandler(new SeekToCurrentErrorHandler(new FixedBackOff(1000L, 2L)));
        factory.setRecordFilterStrategy(new MessageFilter());
//        factory.setRetryTemplate(new RetryTemplate()); only when not a batch listener

        factory.getContainerProperties().setAckMode(AckMode.MANUAL);
        factory.getContainerProperties().setCommitRetries(3);
        factory.getContainerProperties().setCommitLogLevel(Level.INFO);


        return factory;
    }

    @Bean
    public SeekToCurrentErrorHandler eh() {
        return new SeekToCurrentErrorHandler(new FixedBackOff(0L, 3L)); // at least 3
    }
}
