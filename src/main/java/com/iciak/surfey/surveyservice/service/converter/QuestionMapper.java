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
    private final AnswerOptionMapper answerOptionMapper;
    public Question toModel(@NonNull final QuestionEntity entity) {
        return Question.builder()
                .uuid(entity.getUuid())
                .content(entity.getContent())
                .answerOptions(entity.getAnswers().stream()
                        .map(answerOptionMapper::toModel).collect(toList()))
                .build();
    }
}
