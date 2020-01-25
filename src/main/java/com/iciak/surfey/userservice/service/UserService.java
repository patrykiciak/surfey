package com.iciak.surfey.userservice.service;

import com.iciak.surfey.userservice.entity.UserEntity;
import com.iciak.surfey.userservice.exception.UserNotFoundException;
import com.iciak.surfey.userservice.model.User;
import com.iciak.surfey.userservice.repository.UserRepository;
import com.iciak.surfey.userservice.service.mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toModel)
                .collect(toList());
    }

    public Optional<User> find(@NonNull final UUID uuid) {
        return userRepository.findByUuid(uuid)
                .map(userMapper::toModel);
    }

    public void create(@NonNull final User user) {
        userRepository.save(
                UserEntity.builder()
                        .uuid(UUID.randomUUID())
                        .dateOfBirth(user.getDateOfBirth())
                        .login(user.getLogin())
                        .password(user.getPassword())
                        .sex(user.getSex())
                        .build()
        );
    }

    public void update(@NonNull UUID uuid, @NonNull final User user) {
        UserEntity dbUser = userRepository.findByUuid(user.getUuid()).orElseThrow(UserNotFoundException::new);
        userRepository.save(
                UserEntity.builder()
                        .id(dbUser.getId())
                        .uuid(uuid)
                        .dateOfBirth(user.getDateOfBirth())
                        .login(user.getLogin())
                        .password(user.getPassword())
                        .sex(user.getSex())
                        .build()
        );
    }

    public void delete(@NonNull final UUID uuid) {
        userRepository.deleteByUuid(uuid);
    }
}
