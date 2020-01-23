package com.iciak.surfey.surveyservice.service.converter;

import com.iciak.surfey.surveyservice.entity.AnswerOptionEntity;
import com.iciak.surfey.surveyservice.model.AnswerOption;
import org.springframework.stereotype.Component;

@Component
public class AnswerOptionMapper {
    public AnswerOption toModel(final AnswerOptionEntity entity) {
        return AnswerOption.builder()
                .answer(entity.getAnswer())
                .uuid(entity.getUuid())
                .build();
    }

    public AnswerOptionEntity toEntity(final AnswerOption model) {
        return AnswerOptionEntity.builder()
                .answer(model.getAnswer())
                .uuid(model.getUuid())
                .build();
    }
}
