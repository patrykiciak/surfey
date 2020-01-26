package com.iciak.surfey.surveyservice.service.mapper;

import com.iciak.surfey.surveyservice.entity.QuestionEntity;
import com.iciak.surfey.surveyservice.entity.SurveyEntity;
import com.iciak.surfey.surveyservice.model.Question;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Component
@AllArgsConstructor
public class QuestionMapper {
    private final AnswerMapper answerMapper;

    public Question toModel(@NonNull final QuestionEntity entity) {
        return Question.builder()
                .uuid(entity.getUuid())
                .surveyUuid(
                        Optional.ofNullable(entity.getSurvey()).map(SurveyEntity::getUuid).orElse(null)
                )
                .content(entity.getContent())
                .answers(entity.getAnswers().stream()
                        .map(answerMapper::toModel).collect(toList()))
                .build();
    }

    public QuestionEntity createEntity(@NonNull final Question question, final SurveyEntity surveyEntity) {
        return QuestionEntity.builder()
                .survey(surveyEntity)
                .uuid(UUID.randomUUID())
                .content(question.getContent())
                .answers(question.getAnswers().stream()
                        .map(answerMapper::createEntity).collect(toList()))
                .build();
    }
}
