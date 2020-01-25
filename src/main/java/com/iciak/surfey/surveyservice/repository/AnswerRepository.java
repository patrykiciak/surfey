package com.iciak.surfey.surveyservice.repository;

import com.iciak.surfey.surveyservice.entity.AnswerEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AnswerRepository extends JpaRepository<AnswerEntity, Integer> {
    Optional<AnswerEntity> findByUuid(@NonNull final UUID uuid);
}
