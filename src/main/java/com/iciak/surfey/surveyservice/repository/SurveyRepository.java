package com.iciak.surfey.surveyservice.repository;

import com.iciak.surfey.surveyservice.entity.SurveyEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SurveyRepository extends JpaRepository<SurveyEntity, Integer> {

    Optional<SurveyEntity> findByUuid(@NonNull final UUID uuid);

    @Transactional
    void deleteByUuid(@NonNull final UUID uuid);
}