package com.iciak.surfey.surveyservice.repository;

import java.util.Optional;
import java.util.UUID;
import javax.transaction.Transactional;

import com.iciak.surfey.surveyservice.entity.QuestionEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, Integer> {

    Optional<QuestionEntity> findByUuid(@NonNull final UUID uuid);

    @Transactional //REVIEW: Transactional should be at service level, not here
    void deleteByUuid(@NonNull final UUID uuid);
}
