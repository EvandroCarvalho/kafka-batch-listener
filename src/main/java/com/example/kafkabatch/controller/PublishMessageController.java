package com.example.kafkabatch.controller;

import com.example.kafkabatch.model.ClientModel;
import com.example.kafkabatch.sender.Sender;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = "publish")
@RequiredArgsConstructor
public class PublishMessageController {

    private final Sender sender;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private static int message = 0;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public void publish() {
        for (int i = 1; i < 13; i++) {
            final byte[] byteArray = ClientModel.Client.newBuilder()
                    .setUuid(UUID.randomUUID().toString())
                    .setName("message " + ++message)
                    .build().toByteArray();
            sender.send(byteArray);

            LOGGER.info("MENSAGEM ENVIADA " + message);
        }

        for (int i = 1; i < 13; i++) {
            final byte[] byteArray = ClientModel.Client.newBuilder()
                    .setUuid(UUID.randomUUID().toString())
                    .setName("message " + ++message)
                    .build().toByteArray();
            sender.send(byteArray);

            LOGGER.info("MENSAGEM ENVIADA " + message);
        }
    }
}
