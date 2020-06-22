package com.example.kafkabatch.deserializer;

import com.example.kafkabatch.model.ClientModel;
import java.util.Map;
import lombok.SneakyThrows;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;

public class ClientDeserializer implements Deserializer<ClientModel.Client> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @SneakyThrows
    @Override
    public ClientModel.Client deserialize(String s, byte[] bytes) {
        return null;
    }

    @SneakyThrows
    @Override
    public ClientModel.Client deserialize(String topic, Headers headers, byte[] data) {
        data = null;
        return ClientModel.Client.parseFrom(data);
    }

    @Override
    public void close() {

    }
}
