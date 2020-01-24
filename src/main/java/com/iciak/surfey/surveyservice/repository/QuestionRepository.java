package com.iciak.surfey.surveyservice.repository;

import com.iciak.surfey.surveyservice.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, Integer> {

    Optional<QuestionEntity> findByUuid(UUID uuid);
    @Transactional
    void deleteByUuid(UUID uuid);
}
