package com.iciak.surfey.userservice.service.mapper;

import com.iciak.surfey.userservice.entity.UserEntity;
import com.iciak.surfey.userservice.model.User;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserMapper {
    public User toModel(@NonNull final UserEntity user) {
        return User.builder()
                .uuid(user.getUuid())
                .login(user.getLogin())
                .password(user.getPassword())
                .sex(user.getSex())
                .dateOfBirth(user.getDateOfBirth())
                .build();
    }

    public UserEntity createEntity(@NonNull final User user) {
        return UserEntity.builder()
                .uuid(UUID.randomUUID())
                .dateOfBirth(user.getDateOfBirth())
                .login(user.getLogin())
                .password(user.getPassword())
                .sex(user.getSex())
                .build();
    }
}