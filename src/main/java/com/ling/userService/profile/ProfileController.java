package com.ling.userService.profile;

import com.ling.userService.user.User;
import com.ling.userService.user.UserRepository;
import com.ling.userService.user.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class ProfileController {
    private final UserService userService;
    private final UserRepository repository;

    public ProfileController(UserService userService, UserRepository repository) {
        this.userService = userService;
        this.repository = repository;
    }

    @GetMapping("/{username}")
    public ResponseEntity<ProfileResponse> profile(@PathVariable String username) {
        try {
            return ResponseEntity.ok(ProfileResponse.fromUser(userService.getUserByUsername(username)));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
