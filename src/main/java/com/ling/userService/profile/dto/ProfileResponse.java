package com.ling.userService.profile.dto;

import com.ling.userService.user.User;

import java.time.Instant;
import java.util.UUID;

public record ProfileResponse(UUID uuid, String username, Instant createdAt) {
    public static ProfileResponse fromUser(User user) {
        return new ProfileResponse(user.getUuid(), user.getUsername(), user.getCreatedAt());
    }
}
