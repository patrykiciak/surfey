package com.iciak.surfey.surveyservice.service.mapper;

import java.util.UUID;

import com.iciak.surfey.surveyservice.entity.SurveyEntity;
import com.iciak.surfey.surveyservice.model.Survey;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import static java.util.stream.Collectors.toList;

@Component
@AllArgsConstructor
public class SurveyMapper {

    private final QuestionMapper questionMapper;

    public Survey toModel(@NonNull final SurveyEntity surveyEntity) {
        return Survey.builder()
                .name(surveyEntity.getName())
                .uuid(surveyEntity.getUuid())
                .questions(surveyEntity.getQuestions()
                        .stream()
                        .map(questionMapper::toModel)
                        .collect(toList()))
                .build();
    }

    public SurveyEntity createEntity(@NonNull final Survey survey) {
        return SurveyEntity.builder()
                .uuid(UUID.randomUUID()) //REVIEW: static import
                .name(survey.getName())
                .questions(survey.getQuestions()
                        .stream()
                        //REVIEW: Mozesz przeciazyc ta metode bez survey entity, tylko z samym question i to brzydkie (createEntity(question, null) wywolac tam)
                        .map(question ->  questionMapper.createEntity(question, null))
                        .collect(toList()))
                .build();
    }
}