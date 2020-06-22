package com.example.kafkabatch.model;

import java.util.UUID;
import lombok.Data;

@Data
public class Client {

    private UUID uuid;
    private String name;
}
