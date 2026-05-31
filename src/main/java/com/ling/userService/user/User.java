package com.ling.userService.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @Id
    private UUID uuid;

    @Column(nullable = false, unique = true, updatable = false)
    private String username;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private Instant createdAt;

    public User(UUID uuid, String username) {
        this.uuid = uuid;
        this.username = username;
    }

    public User() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
