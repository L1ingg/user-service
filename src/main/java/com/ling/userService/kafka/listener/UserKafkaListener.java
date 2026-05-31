package com.ling.userService.kafka.listener;

import com.ling.userService.kafka.listener.dto.event.RegisteredEvent;
import com.ling.userService.user.User;
import com.ling.userService.user.UserService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserKafkaListener {

    private final UserService userService;

    public UserKafkaListener(UserService userService) {
        this.userService = userService;
    }

    @KafkaListener(topics = "user.registered")
    public void consume(String json) {
        RegisteredEvent event = RegisteredEvent.fromJson(json);
        userService.create(new User(UUID.fromString(event.uuid()), event.username()));
    }
}
