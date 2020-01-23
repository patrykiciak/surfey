package com.iciak.surfey.surveyservice.repository;

import com.iciak.surfey.surveyservice.entity.QuestionResponseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface QuestionResponseRepository extends JpaRepository<QuestionResponseEntity, Integer> {
    Optional<QuestionResponseEntity> findByUuid(UUID uuid);
    void deleteByUuid(UUID uuid);
}
