package com.ling.userService.user;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User save(User user) {
        return repository.save(user);
    }

    public User create(User user) {
        if (repository.existsByUsername(user.getUsername())) {
            throw new EntityExistsException("Пользователь с таким username уже существует");
        }
        return save(user);
    }

    public User getUserByUuid(UUID uuid) {
        return repository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + uuid));
    }

    public User getUserByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + username));
    }
}
