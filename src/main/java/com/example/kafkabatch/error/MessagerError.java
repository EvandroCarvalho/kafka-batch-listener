package com.example.kafkabatch.error;

import com.example.kafkabatch.model.ClientModel.Client;

public class MessagerError extends RuntimeException {

    private Client client;
    private String msg;

    public MessagerError(Client client, String msg) {
        this.client = client;
        this.msg = msg;
    }
}
