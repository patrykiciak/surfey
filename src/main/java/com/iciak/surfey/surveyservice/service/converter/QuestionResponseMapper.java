package com.iciak.surfey.surveyservice.service.converter;

import com.iciak.surfey.surveyservice.entity.QuestionResponseEntity;
import com.iciak.surfey.surveyservice.model.QuestionResponse;
import com.iciak.surfey.userservice.service.mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Getter
public class QuestionResponseMapper {
    private final AnswerOptionMapper answerOptionMapper;
    private final UserMapper userMapper;


    public QuestionResponse toModel(QuestionResponseEntity entity) {
        return QuestionResponse.builder()
                .user(userMapper.toModel(entity.getUser()))
                .chosenAnswer(answerOptionMapper.toModel(entity.getChosenAnswer()))
                .uuid(entity.getUuid())
                .build();
    }
}
