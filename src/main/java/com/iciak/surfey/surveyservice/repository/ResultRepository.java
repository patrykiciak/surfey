package com.iciak.surfey.surveyservice.repository;

import java.util.Optional;
import java.util.UUID;
import javax.transaction.Transactional;

import com.iciak.surfey.surveyservice.entity.ResultEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepository extends JpaRepository<ResultEntity, Integer> {

    Optional<ResultEntity> findByUuid(@NonNull final UUID uuid);

    @Transactional //REVIEW: tranascional should be on service level
    void deleteByUuid(@NonNull final UUID uuid);
}
