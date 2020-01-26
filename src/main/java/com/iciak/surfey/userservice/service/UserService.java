package com.iciak.surfey.userservice.service;

import com.iciak.surfey.userservice.entity.UserEntity;
import com.iciak.surfey.userservice.exception.UserNotFoundException;
import com.iciak.surfey.userservice.model.User;
import com.iciak.surfey.userservice.repository.UserRepository;
import com.iciak.surfey.userservice.service.mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
        userRepository.save(userMapper.createEntity(user));
    }

    @Transactional
    public void update(@NonNull final UUID uuid, @NonNull final User user) {
        UserEntity userEntity = userRepository.findByUuid(uuid).orElseThrow(UserNotFoundException::new);
        userEntity.setDateOfBirth(user.getDateOfBirth());
        userEntity.setLogin(user.getLogin());
        userEntity.setPassword(user.getPassword());
        userEntity.setSex(user.getSex());
    }

    public void delete(@NonNull final UUID uuid) {
        userRepository.deleteByUuid(uuid);
    }
}
