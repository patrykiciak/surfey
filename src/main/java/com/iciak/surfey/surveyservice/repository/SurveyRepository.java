package com.iciak.surfey.surveyservice.repository;

import java.util.Optional;
import java.util.UUID;
import javax.transaction.Transactional;

import com.iciak.surfey.surveyservice.entity.SurveyEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface SurveyRepository extends JpaRepository<SurveyEntity, Integer> {

    Optional<SurveyEntity> findByUuid(@NonNull final UUID uuid);

    @Transactional //REVIEW: transactional on service
    void deleteByUuid(@NonNull final UUID uuid);
}