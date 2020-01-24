package com.iciak.surfey.surveyservice.service.converter;

import com.iciak.surfey.surveyservice.entity.AnswerEntity;
import com.iciak.surfey.surveyservice.model.Answer;
import org.springframework.stereotype.Component;

@Component
public class AnswerMapper {
    public Answer toModel(final AnswerEntity entity) {
        return Answer.builder()
                .content(entity.getContent())
                .uuid(entity.getUuid())
                .build();
    }

    public AnswerEntity toEntity(final Answer model) {
        return AnswerEntity.builder()
                .content(model.getContent())
                .uuid(model.getUuid())
                .build();
    }
}
