package com.iciak.surfey.surveyservice.service.mapper;

import com.iciak.surfey.surveyservice.entity.AnswerEntity;
import com.iciak.surfey.surveyservice.model.Answer;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AnswerMapper {
    public Answer toModel(@NonNull final AnswerEntity entity) {
        return Answer.builder()
                .content(entity.getContent())
                .uuid(entity.getUuid())
                .build();
    }

    public AnswerEntity createEntity(@NonNull final Answer entity) {
        return AnswerEntity.builder()
                .content(entity.getContent())
                .uuid(UUID.randomUUID())
                .build();
    }
}
