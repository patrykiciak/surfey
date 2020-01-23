package com.iciak.surfey.userservice.repository;

import com.iciak.surfey.userservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByUuid(UUID uuid);
    @Transactional
    void deleteByUuid(UUID uuid);
}
