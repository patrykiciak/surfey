package com.iciak.surfey.userservice.controller;

import java.util.UUID;

import com.iciak.surfey.userservice.model.User;
import com.iciak.surfey.userservice.model.Users;
import com.iciak.surfey.userservice.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    //REVIEW: it can return void
    //REVIEW: use Annotation @ResponseStatus(NO_CONTENT)
    public ResponseEntity create(@RequestBody @NonNull final User user) {
        userService.create(user);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{uuid}")
    //REVIEW: it can return void
    //REVIEW: use Annotation @ResponseStatus(NO_CONTENT)
    public ResponseEntity update(@PathVariable @NonNull final UUID uuid, @RequestBody @NonNull final User user) {
        userService.update(uuid, user);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{uuid}")
    //REVIEW: it can return void
    //REVIEW: use Annotation @ResponseStatus(NO_CONTENT)
    public ResponseEntity delete(@PathVariable @NonNull final UUID uuid) {
        userService.delete(uuid);
        return ResponseEntity.ok().build();
    }
}
