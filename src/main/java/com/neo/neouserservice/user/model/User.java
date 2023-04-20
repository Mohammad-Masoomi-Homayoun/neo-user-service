package com.neo.neouserservice.user.model;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.Serializable;
import java.util.UUID;

@Data
public class User implements Serializable {

//    UUID
    private String id = UUID.randomUUID().toString();

    private String name;

}
