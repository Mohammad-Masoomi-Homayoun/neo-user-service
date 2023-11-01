package com.neo.neouserservice.user.web.controller;

import java.util.Optional;

public enum InfoType {
    FULL,
    BASIC;

    public static InfoType fromString(String value) {
        return Optional.ofNullable(value)
                .map(String::toUpperCase)
                .map(InfoType::valueOf)
                .orElse(null);
    }
}
