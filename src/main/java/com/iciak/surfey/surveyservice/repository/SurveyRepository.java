package com.iciak.surfey.surveyservice.repository;

import com.iciak.surfey.surveyservice.entity.SurveyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SurveyRepository extends JpaRepository<SurveyEntity, Integer> {
    Optional<SurveyEntity> findByUuid(UUID uuid);
}