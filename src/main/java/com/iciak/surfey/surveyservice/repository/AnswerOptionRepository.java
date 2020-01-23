package com.iciak.surfey.surveyservice.repository;

import com.iciak.surfey.surveyservice.entity.AnswerOptionEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AnswerOptionRepository extends JpaRepository<AnswerOptionEntity, Integer> {
    Optional<AnswerOptionEntity> findByUuid(@NonNull UUID uuid);
}
