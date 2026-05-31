package com.ling.userService.kafka.listener.dto.event;

import tools.jackson.databind.ObjectMapper;

public record RegisteredEvent(String uuid, String username, String email) {
    public static RegisteredEvent fromJson(String json) {
        return new ObjectMapper().readValue(json, RegisteredEvent.class);
    }
}
