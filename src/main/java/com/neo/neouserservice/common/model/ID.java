package com.neo.neouserservice.common.model;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class ID implements Serializable {

    private String id;

    public static ID generateId() {
        return new ID(UUID.randomUUID().toString());
    }

    public ID(String id) {
        this.id = id;
    }

    public ID() {

    }

    public static ID of(String id) {
        return new ID(id);
    }

    public String toString() {
        return id;
    }
}
