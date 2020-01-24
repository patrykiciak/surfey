package com.iciak.surfey.surveyservice.service.converter;

import com.iciak.surfey.surveyservice.entity.QuestionEntity;
import com.iciak.surfey.surveyservice.model.Question;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import static java.util.stream.Collectors.toList;

@Component
@AllArgsConstructor
public class QuestionMapper {
    private final AnswerMapper answerMapper;

    public Question toModel(@NonNull final QuestionEntity entity) {
        return Question.builder()
                .uuid(entity.getUuid())
                .content(entity.getContent())
                .answers(entity.getAnswers().stream()
                        .map(answerMapper::toModel).collect(toList()))
                .build();
    }

    public QuestionEntity toEntity(Question question) {
        return QuestionEntity.builder()
                .uuid(question.getUuid())
                .content(question.getContent())
                .answers(question.getAnswers().stream()
                        .map(answerMapper::toEntity).collect(toList()))
                .build();
    }
}
