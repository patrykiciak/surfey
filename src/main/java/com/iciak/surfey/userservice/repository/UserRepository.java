package com.iciak.surfey.userservice.repository;

import java.util.Optional;
import java.util.UUID;
import javax.transaction.Transactional;

import com.iciak.surfey.userservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByUuid(UUID uuid);
    @Transactional //SHOULD BE IN SERVICE
    void deleteByUuid(UUID uuid);
}
