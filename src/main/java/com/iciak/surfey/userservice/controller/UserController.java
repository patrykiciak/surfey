package com.iciak.surfey.userservice.controller;

import com.iciak.surfey.userservice.model.User;
import com.iciak.surfey.userservice.model.Users;
import com.iciak.surfey.userservice.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<Users> findAll() {
        return ResponseEntity.ok(
                Users.builder()
                        .users(userService.findAll())
                        .build()
        );
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<User> find(@PathVariable @NonNull final UUID uuid) {
        return userService.find(uuid)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity create(@RequestBody @NonNull final User user) {
        userService.create(user);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{uuid}")
    public ResponseEntity update(@PathVariable @NonNull final UUID uuid, @RequestBody @NonNull final User user) {
        userService.update(uuid, user);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity delete(@PathVariable @NonNull final UUID uuid) {
        userService.delete(uuid);
        return ResponseEntity.ok().build();
    }
}
