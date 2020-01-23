package com.iciak.surfey.userservice.service.mapper;

import com.iciak.surfey.userservice.entity.UserEntity;
import com.iciak.surfey.userservice.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toModel(final UserEntity user) {
        return User.builder()
                .uuid(user.getUuid())
                .login(user.getLogin())
                .password(user.getPassword())
                .sex(user.getSex())
                .dateOfBirth(user.getDateOfBirth())
                .build();
    }

    public UserEntity toEntity(final User user) {
        return UserEntity.builder()
                .uuid(user.getUuid())
                .login(user.getLogin())
                .password(user.getPassword())
                .sex(user.getSex())
                .dateOfBirth(user.getDateOfBirth())
                .build();
    }


}