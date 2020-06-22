package com.example.kafkabatch.config;

import com.example.kafkabatch.model.ClientModel.Client;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;

public class ErrorDeserializer extends ErrorHandlingDeserializer<Client> {
}
